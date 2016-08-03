package com.forerunner.core.sso;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

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
	
	private static final String LONIN_ERROR_KEY="login_error";
	
	public static int num=0;
	//5次登录
	private static final int degree=5;
	/**
	 * 登录 验证密码是否存在
	 * @param account
	 * @param request
	 */
	public static LoginMsg login(HttpServletRequest request,String password,String username){
		//String ip=CommUtil.getIpAddr(request);
		//查询该IP地址是否已经在黑名单中!
		AccountService accountService=SpringUtils.getBean(AccountService.class);
		Account account=accountService.getAccountByUsername(username);
		LoginMsg msg=new LoginMsg();
		if(account==null){
			return msg;
		}
		if(account.getEnablead()){
			msg.setPubliErrorMsg("帐号已经被锁定!");
			return msg;
		}		
		SessionTool tool = SessionTool.getInstance(request);
		try{	
			String findPwd=CommUtil.encrypt(password, account.getSalt());
			if(!findPwd.equals(account.getPassword())){
				msg.setUserErrorMsg(null);
				//处理密码输入错误 如果同一个帐号连续输入错误5次 则锁定该帐号
				//int cishu=addErrorNum(account.getId(),tool,request,account);	
				msg.setPasswordErrorMsg("密码错误");
				/*if(degree-cishu<1){
					msg.setPubliErrorMsg("帐号已经被锁定!");
					return msg;
				}*/
				//msg.setPubliErrorMsg("密码不正确,还有"+(degree-cishu)+"次机会.");
				return msg;
			}
		}catch(IllegalArgumentException e){
			msg.setPubliErrorMsg(e.getMessage());
		}catch(Exception e){
			msg.setPubliErrorMsg(e.getMessage());
		}
		if(tool.exists(LONIN_ERROR_KEY)){
			tool.del(LONIN_ERROR_KEY);
		}
		LoginUser loginUser=new LoginUser(username,account);
		tool.set(LOGIN_KEY, JSON.toJSONString(loginUser));
		addLoginNum();
		return msg;
	}
	/**
	 * 把错误信息加入到session中 如果超过10个账号 则系统判断为恶意攻击
	 * @param id
	 * @param tool
	 * @return
	 */
	private static int addErrorNum(Long id,SessionTool tool,HttpServletRequest request,Account account){
		int degree1=1;
		if(tool.exists(LONIN_ERROR_KEY)){
			StringBuilder sb=new StringBuilder();
			String key=CommUtil.null2String(tool.get(LOGIN_KEY));
			String[] array=key.split(",");
			if(array.length>10){
				//把该IP加入黑名单....待实现!
				
				Assert.isTrue(false,"该IP：["+CommUtil.getIpAddr(request)+"];存在恶意攻击,已经加入黑名单，被限制了登陆");
			}
			boolean isNew1=false;
			int i=0;
			for(String arr:array){
				if(i>0){
					sb.append(",");
				}
				i++;
				String[] array1=arr.split("_");
				boolean isNew=true;
				if(array1.length==2){
					Long id1=CommUtil.null2Long(array1[0]);
					if(id1.longValue()==id.longValue()){
						isNew=false;
						isNew1=false;
						Integer num=CommUtil.null2Int(array1[1]);
						if(num>degree){
							//锁定帐号
							AccountService accountService=SpringUtils.getBean(AccountService.class);
							account.setEnablead(true);
							accountService.save(account);
							if(log.isDebugEnabled()){
								log.debug("账号已经被锁定！");
							}
							Assert.isTrue(false,"账号已经被锁定！");
						}else{
							num++;
							degree1=num;
							sb.append(id+"_"+num);
						}
					}
				}
				if(isNew){
					sb.append(arr);
				}
			}
			if(isNew1){
				sb.append(",").append(id).append("_").append(degree1);
			}
			tool.set(LONIN_ERROR_KEY, sb.toString());
		}else{
			tool.set(LONIN_ERROR_KEY, id+"_"+degree1);
		}
		return degree1;
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
	 * 获取登录用户
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getAccount(HttpServletRequest request) {
		SessionTool tool = SessionTool.getInstance(request);
		String json = CommUtil.null2String(tool.get(LOGIN_KEY));
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Map<String,Object> loginUser = JSON.parseObject(json,Map.class);
		return loginUser;
	}
	/**
	 * 退出登录
	 */
	public static void loginOut(HttpServletRequest request){		
		SessionTool tool = SessionTool.getInstance(request);
		if(tool.exists(LOGIN_KEY)){
			tool.del(LOGIN_KEY);
		}
		deleteLoginNum();
	}
}
