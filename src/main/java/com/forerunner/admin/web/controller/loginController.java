package com.forerunner.admin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.forerunner.core.service.system.AccountService;
import com.forerunner.core.tool.CommUtil;
import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.foundation.domain.po.system.Account;
import com.forerunner.web.controller.BaseController;
import com.google.common.collect.Maps;

/**
 * 登录,注册,找回密码设置
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/sso")
public class loginController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(loginController.class);
	
	@Autowired
	private AccountService accountService;

	/**
	 * 后台登录页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login.htm")
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/login");
		CommonParams.loadParams(view, "登录");
		return view;
	}
	/**
	 * 登录方法 限定POST方法
	 * 如果帐号连续登录5次 则将该帐号锁定
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/doLogin.htm",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=true) String username, @RequestParam(required=true) String password) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("flag", false);
		Account account = this.accountService.getAccountByUsername(username);
		if(account==null){
			result.put("errorMsg", "账号不存在");
			return result;
		}
		if(log.isDebugEnabled()){
			log.debug("Login Account:"+JSON.toJSONString(account));
		}
		Boolean enablead=account.getEnablead();
		if(enablead){
			result.put("errorMsg", "账号已经被锁住");
			return result;
		}
		String salt=account.getSalt();
		String findpwd = CommUtil.encrypt(password, salt);
		if (!findpwd.equals(account.getPassword())) {
			//密码错误 把错误次数加入Session中
			result.put("errorMsg", "密码错误");
			return result;
		}
		return result;
	}

}
