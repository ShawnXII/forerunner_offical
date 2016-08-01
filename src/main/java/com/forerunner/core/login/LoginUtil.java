package com.forerunner.core.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.forerunner.core.service.system.AccountService;
import com.forerunner.core.tool.CommUtil;
import com.forerunner.core.tool.SessionTool;
import com.forerunner.core.tool.SpringUtils;
import com.forerunner.foundation.domain.po.system.Account; 

/**
 * 登录配置 
 * @author Administrator
 *
 */
public class LoginUtil {
	
	private static final Logger log = LoggerFactory.getLogger(LoginUtil.class);
	
	private static final String LOGIN_KEY="login_key";
	
	private static int num=0;
	//5次登录
	private static final int degree=5;
	/**
	 * 登录 验证密码是否存在
	 * @param account
	 * @param request
	 */
	public static LoginMsg login(Account account,HttpServletRequest request,String password,String username){
		LoginMsg msg=new LoginMsg();
		if(account==null){
			return msg;
		}
		String findPwd=CommUtil.encrypt(password, account.getSalt());
		if(!findPwd.equals(account.getPassword())){
			//处理密码输入错误 如果同一个帐号连续输入错误5次 则锁定该帐号
			SessionTool tool = SessionTool.getInstance(request);
			if(tool.exists(LOGIN_KEY)){
				LoginUser loginUser=new LoginUser(username,null);
				loginUser.setEnableadMsg("");
				tool.set(LOGIN_KEY, "");
			}
			msg.setPasswordErrorMsg("密码错误");
			msg.setPubliErrorMsg("密码不正确,还有'+num+'次机会.");
			return msg;
		}	
		return msg;
	}
	/**
	 * 保存登录信息 ID是key 每次登录时查找该帐号是否已经登录 value 存的登录信息 登录次数 登录ip 加入Cookie里面
	 * @param account
	 */
	public static void save(Account account, HttpServletRequest request) {
		// 30分钟不做如何操作则删除session
		if (account != null) {
			SessionTool tool = SessionTool.getInstance(request);			
			String json=JSON.toJSONString(account);
			tool.set(LOGIN_KEY, json);
			if(log.isDebugEnabled()){
				log.debug("登录:"+JSON.toJSONString(account));
			}
		}
	}
	/**
	 * 登录人数添加 有锁
	 */
	public static synchronized void addLoginNum(){
		num++;
	}
	/**
	 * 登录人数统计
	 * @return
	 */
	public static int getLoginNum(){
		return num;
	}
	/**
	 * 登录人数减
	 */
	public static synchronized void deleteLoginNum(){
		num--;		
	}
	
	
	/**
	 * 锁定帐号
	 * @param account
	 * @param request
	 * @param response
	 */
	public static void locking(Account account, HttpServletRequest request){
		if(account!=null){
			account.setEnablead(true);
			//帐号锁定
			AccountService accountFacadeService=SpringUtils.getBean("accountService");
			Account acc=accountFacadeService.saveOrUpdate(account);
			//记录日志			
			//把用户资料从redis中删除
//			if(redisService.exists(acc.getId())){
//				redisService.del(acc.getId());
//			}			
		}
	}

	/**
	 * 获取登录用户
	 * 
	 * @param request
	 * @return
	 */
	public static Account getAccount(HttpServletRequest request) {
		SessionTool tool = SessionTool.getInstance(request);
		String json = CommUtil.null2String(tool.get(LOGIN_KEY));
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Account account = JSON.parseObject(json,Account.class);
		return account;
	}
	/**
	 * 退出登录
	 */
	public static void loginOut(HttpServletRequest request){		
		SessionTool tool = SessionTool.getInstance(request);
		if(tool.exists(LOGIN_KEY)){
			tool.del(LOGIN_KEY);
		}
	}
}
