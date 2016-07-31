package com.forerunner.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.web.controller.BaseController;

@Controller
@RequestMapping(value="/admin/system")
public class SystemController extends BaseController{
	/**
	 * 系统设置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value={"/setUp.htm"})
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view=new ModelAndView();
		view.setViewName("/admin/system/setUp");
		CommonParams.loadParams(view,"系统设置")	;
		return view;
	}
}
