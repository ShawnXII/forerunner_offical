package com.forerunner.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.service.trends.PressService;
import com.forerunner.core.tool.CommUtil;
import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.web.controller.BaseController;

/**
 * 新闻咨询管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/admin/article")
public class ArticleController extends BaseController{
	
	@Autowired
	private PressService pressService;
	
	@RequestMapping(value = "articleList")
	public ModelAndView toArticleList(HttpServletRequest request, HttpServletResponse response){
		Integer pageIndex=CommUtil.null2Int(request.getParameter("pageIndex"), 0);
		Integer pageSize=CommUtil.null2Int(request.getParameter("pageSize"),20);
		ModelAndView view = new ModelAndView();
		view.setViewName("/index");
		CommonParams.loadParams(view, "新闻列表");
		Pageable pageable=new PageRequest(pageIndex,pageSize);
		//pressService.findAll(null, pageable);
		/*new SearchFilter("username",Operator.EQ,"ppt");*/
		return view;
	}
	
}
