package com.forerunner.admin.web.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.service.system.AccountService;
import com.forerunner.core.sso.EnrollUtil;
import com.forerunner.core.sso.LoginMsg;
import com.forerunner.core.sso.LoginUser;
import com.forerunner.core.sso.LoginUtil;
import com.forerunner.core.tool.BeanUtil;
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
		view.setViewName("/admin/sso/login");
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
		LoginMsg msg=LoginUtil.login(request, password, username);
		result=BeanUtil.transBean2Map(msg);
		return result;
	}
	/**
	 * 测试方法 获取当前登录用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getLogin.htm")
	@ResponseBody
	public Map<String,Object> getLogin(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = LoginUtil.getAccount(request);
		return result;
	}
	/**
	 * 注册页面
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/enroll.htm")
	public ModelAndView toEnroll(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/sso/enroll");
		CommonParams.loadParams(view, "注册");
		return view;
	}
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doEnroll.htm",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doEnroll(HttpServletRequest request, HttpServletResponse response,
			String username,String password){
		Map<String,Object> result=Maps.newHashMap();
		result.put("flag", false);
		Account account=new Account();
		account.setUsername(username);
		account.setPassword(password);
		try{
			if(account!=null){
				EnrollUtil.enroll(account);
				result.put("flag", true);
				//如果注册成功 则进行登陆
				LoginUtil.login(request, password, username);
			}
		}catch(Exception e){
			result.put("errorMsg", e.getMessage());
		}		
		return result;
	}
	/**
	 * 检查帐号是否存在
	 * @param request
	 * @param response
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/check/{username}.htm",method = RequestMethod.POST)
	public Boolean check(HttpServletRequest request, HttpServletResponse response,@PathVariable("username") String username){
		if(StringUtils.isBlank(username)){
			return false;
		}
		Account account=accountService.getAccountByUsername(username);
		return account!=null;
	}
	
	
}
