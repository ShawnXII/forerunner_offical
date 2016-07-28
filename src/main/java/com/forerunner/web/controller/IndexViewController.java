package com.forerunner.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexViewController {
	
	
	@RequestMapping(value = {"/index.htm","/"})
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view=new ModelAndView();
		view.setViewName("/index");
		view.addObject("ctxStatic", "/static");
		return view;
	}
	
}
