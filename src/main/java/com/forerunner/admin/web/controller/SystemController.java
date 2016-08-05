package com.forerunner.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.service.system.MenuService;
import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.web.controller.BaseController;
/**
 * 系统设置,用户,权限等系统管理
 * @author Administrator
 */
@Controller
@RequestMapping(value="/admin/system")
public class SystemController extends BaseController{
	
	@Autowired
	private MenuService menuService;	
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
		CommonParams.loadParams(view,"系统设置");
		return view;
	}
	/**
	 * 系统菜单列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/menuList.htm")
	public ModelAndView toMenuView(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view=new ModelAndView();
		view.setViewName("/admin/system/menuList");
		CommonParams.loadParams(view,"系统菜单列表");
		return view; 
	}
	
}
