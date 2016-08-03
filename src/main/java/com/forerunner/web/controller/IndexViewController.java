package com.forerunner.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.web.resource.CommonParams;

/**
 * 前端页面
 * 
 * @author Administrator
 *
 */
@Controller
public class IndexViewController {

	/**
	 * 主页 推荐新闻 推荐产品 推荐图文新闻
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/index.htm", "/" })
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/index");
		CommonParams.loadParams(view, "主页");
		return view;
	}

	/**
	 * 产品详情页面
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/product/{id}.htm" })
	public ModelAndView toProduct(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/product");
		CommonParams.loadParams(view, "产品1");
		return view;
	}

	/**
	 * 产品列表 分页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/product.htm" })
	public ModelAndView productList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/productList");
		CommonParams.loadParams(view, "产品列表");
		return view;
	}

	/**
	 * 新闻咨询列表 分页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/news.htm" })
	public ModelAndView newsList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/productList");
		CommonParams.loadParams(view, "产品列表");
		return view;
	}

	/**
	 * 获取产品数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/productData/{pageIndex}/{pageSize}.htm" })
	public Map<String, Object> productListData(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize) {
		return null;
	}
	/**
	 * 获取新闻数据
	 * @param request
	 * @param response
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = { "/newsData/{pageIndex}/{pageSize}.htm" })
	public Map<String, Object> newsListData(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize) {
		return null;
	}
	
}
