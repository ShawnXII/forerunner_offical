package com.forerunner.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.web.controller.BaseController;

@Controller
@RequestMapping(value="/sso")
public class loginController extends BaseController{
	/**
	 * 后台登录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login.htm")
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view=new ModelAndView();
		view.setViewName("/admin/login");
		CommonParams.loadParams(view,"登录")	;
		return view;
	}
	
	
}
