package com.forerunner.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.web.controller.BaseController;

@Controller
@RequestMapping(value="/admin")
public class AdminIndexController extends BaseController{
	
	@RequestMapping(value={"/","/index.htm"})
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view=new ModelAndView();
		view.setViewName("/admin/index");
		CommonParams.loadParams(view,"主页")	;
		return view;
	}
}
