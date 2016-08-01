package com.forerunner.core.sso;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.forerunner.core.service.system.AccountService;
import com.forerunner.core.tool.CommUtil;
import com.forerunner.core.tool.SpringUtils;
import com.forerunner.foundation.domain.po.system.Account;

/**
 * 注册工具类
 * @author wx
 *
 */
public class EnrollUtil {
	/**
	 * 注册
	 * @param account
	 */
	public static void enroll(Account account){
		Assert.isTrue(account!=null,"信息不能为空");
		String username=account.getUsername();
		Assert.isTrue(StringUtils.isNotBlank(username),"用户名不能为空");
		String password=account.getPassword();
		Assert.isTrue(StringUtils.isNotBlank(password),"用户名不能为空");
		AccountService accountService=SpringUtils.getBean(AccountService.class);
		Account acc=accountService.getAccountByUsername(username);
		Assert.isTrue(acc==null,"该帐号已经注册过");
		if(CommUtil.isMobile(username)){
			account.setUsername("");
			account.setMobile(username);
			account.setValidateMobile(true);
		}else if(CommUtil.isEmail(username)){
			account.setUsername("");
			account.setEmail(username);
			account.setValidateEmail(true);
		}else{
			account.setUsername(username);
		}
		String  salt=CommUtil.getSalt();
		
		account.setPassword(CommUtil.encrypt(password, salt));
		account.setEnablead(false);
		account.setStatus(0);
		account.setDeleteStatus(false);
		account.setCreateTime(new Date());
		account.setCreateBy("enroll");
		account.setHeadPortrait("");
		if(StringUtils.isBlank(account.getNickname())){
			account.setNickname("管理员_"+username);
		}
		accountService.saveOrUpdate(account);
	}
}
