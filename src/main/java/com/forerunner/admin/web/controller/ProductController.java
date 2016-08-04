package com.forerunner.admin.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.service.product.ProductService;
import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.foundation.domain.po.product.Product;
import com.forerunner.web.controller.BaseController;
import com.google.common.collect.Maps;

/**
 * 产品管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/admin/product")
public class ProductController extends BaseController {
	@Autowired
	private ProductService productService;

	/**
	 * ajax获取产品分页信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/productListData/{pageIndex}/{pageSize}.htm")
	@ResponseBody
	public Map<String, Object> getProductList(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pageIndex") Integer pageIndex, @PathVariable("pageSize") Integer pageSize) {
		Map<String, Object> map = Maps.newHashMap();
		return map;
	}
	/**
	 * 产品列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/productList.htm")
	public ModelAndView productList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	
	/**
	 * 产品新增 
	 * 进入页面之前检查是否有分类 品牌 规格信息 如果没有 则返回新增分类 品牌 规格页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add.htm")
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/add");
		CommonParams.loadParams(view, "新增");
		return view;
	}
	/**
	 * 产品修改
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/edit.htm")
	public ModelAndView editProduct(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/edit");
		CommonParams.loadParams(view, "修改");
		return view;
	}
	/**
	 * 品牌列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/brandList.htm")
	public ModelAndView brandList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	/**
	 * 添加品牌
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addBrand.htm")
	public ModelAndView addBrand(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	/**
	 * 修改品牌
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editBrand.htm")
	public ModelAndView editBrand(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/edit");
		CommonParams.loadParams(view, "修改");
		return view;
	}
	/**
	 * 分类列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/classifyList.htm")
	public ModelAndView classifyList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addClassify.htm")
	public ModelAndView addClassify(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editClassify.htm")
	public ModelAndView editClassify(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/edit");
		CommonParams.loadParams(view, "修改");
		return view;
	}
	/**
	 * 规格列表 只可以新增 不可修改和删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/propertyList.htm")
	public ModelAndView propertyList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	/**
	 * 新增规格
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addProperty.htm")
	public ModelAndView addProperty(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	/**
	 * 修改规格
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editProperty.htm")
	public ModelAndView editProperty(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Product product=new Product();
		List<Product> list=productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}
	
}
