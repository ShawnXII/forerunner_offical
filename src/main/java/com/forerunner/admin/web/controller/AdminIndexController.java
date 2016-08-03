package com.forerunner.admin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.service.system.AccountService;
import com.forerunner.core.sso.LoginUtil;
import com.forerunner.core.tool.CommUtil;
import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.foundation.domain.po.system.Account;
import com.forerunner.web.controller.BaseController;

@Controller
@RequestMapping(value="/admin")
public class AdminIndexController extends BaseController{
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value={"/","/index.htm"})
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view=new ModelAndView();
		view.setViewName("/admin/index");
		CommonParams.loadParams(view,"主页")	;
		Map<String,Object> map=LoginUtil.getAccount(request);
		Long id=CommUtil.null2Long(map.get("id"));
		Account account=accountService.findOne(id);
		return view;
	}
		
}
