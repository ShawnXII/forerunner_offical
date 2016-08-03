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
		map.put("product", this.productService.findProduct());
		return map;
	}

	@RequestMapping(value = "/productList.htm")
	public ModelAndView productList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/index");
		CommonParams.loadParams(view, "产品列表");
		Page<Product> page=this.productService.findProduct();
		List<Product> productList=page.getContent();
		int size=page.getSize();
		long totalElements=page.getTotalElements();
		int totalPages=page.getTotalPages();
		view.addObject("size", size);
		view.addObject("totalElements", totalElements);
		view.addObject("totalPages", totalPages);
		view.addObject("productList", productList);
		return view;
	}

}
