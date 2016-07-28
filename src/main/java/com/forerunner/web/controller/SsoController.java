package com.forerunner.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SsoController {
	
	@RequestMapping(value = {"/login"})
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view=new ModelAndView();
		System.out.println("123123123123213123123");
		view.setViewName("/login");
		return view;
	}
}
