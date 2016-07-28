package com.forerunner.core.tool;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.forerunner.core.service.system.AccountService;
import com.forerunner.foundation.domain.po.system.Account; 

/**
 * 登录缓存配置
 * 
 * @author Administrator
 *
 */
public class LoginUtil {
	
	private static final Logger log = LoggerFactory.getLogger(LoginUtil.class);
	
	private static final String LOGIN_KEY="login_key";
	
	private static final String LOGIN_PASSWORD_ERROR="login_password_error";
	private static int num=0;
	/**
	 * 保存登录信息 ID是key 每次登录时查找该帐号是否已经登录 value 存的登录信息 登录次数 登录ip 加入Cookie里面
	 * 
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
	 * 获取登录失败次数
	 * @param id
	 * @param request
	 * @return
	 */
	public static int  getPasswordError(String id, HttpServletRequest request){
		if(StringUtils.isBlank(id)){
			return 1;
		}
		int num=1;
		SessionTool tool = SessionTool.getInstance(request);
		if(tool.exists(LOGIN_PASSWORD_ERROR)){
			String value=CommUtil.null2String(tool.get(LOGIN_PASSWORD_ERROR));
			String[] arr=value.split(",");
			for(String a:arr){
				String[] arr1=a.split("_");
				String aid=arr1[0];
				if(aid.equals(id)){
					num=CommUtil.null2Int(arr1[1],1);
					break;
				}
			}
		}
		return num;
	}
	public static void deleteError( HttpServletRequest request){
		SessionTool tool = SessionTool.getInstance(request);
		if(tool.exists(LOGIN_PASSWORD_ERROR)){
			tool.del(LOGIN_PASSWORD_ERROR);
		}
	}
	/**
	 * 用户登录密码输入错误次数
	 * @param id
	 * @param request
	 */
	public static int addError(String id, HttpServletRequest request){
		if(StringUtils.isBlank(id)){
			return 0;
		}
		int num=1;
		int time=2*60*60;
		SessionTool tool = SessionTool.getInstance(request);
		if(tool.exists(LOGIN_PASSWORD_ERROR)){
			//存在
			String value=CommUtil.null2String(tool.get(LOGIN_PASSWORD_ERROR));
			String[] arr=value.split(",");
			boolean flag=false;
			StringBuilder sb=new StringBuilder();
			int i=0;
			for(String a:arr){
				if(i>0){
					sb.append(",");
				}
				String[] arr1=a.split("_");
				String aid=arr1[0];
				Integer num1=CommUtil.null2Int(arr1[1],1);
				if(aid.equals(id)){
					num1++;
					flag=true;
					num=num1;
					sb.append(id).append("_").append(num1);
				}else{
					sb.append(a);
				}
				i++;
			}
			if(!flag){
				sb.append(",").append(id).append("_").append(1);
			}
			if(log.isDebugEnabled()){
				log.debug("登录密码输入错误次数:"+sb.toString());
			}
			tool.set(LOGIN_PASSWORD_ERROR, sb.toString(),time);
		}else{
			String str=id+"_"+1;
			if(log.isDebugEnabled()){
				log.debug("登录密码输入错误次数:"+str);
			}
			tool.set(LOGIN_PASSWORD_ERROR, str,time);
		}
		return num;
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
			Account acc=accountFacadeService.save(account);
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
