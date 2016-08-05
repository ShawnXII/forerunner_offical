package com.forerunner.admin.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.forerunner.core.search.Searchable;
import com.forerunner.core.service.product.BrandService;
import com.forerunner.core.service.product.ClassifyService;
import com.forerunner.core.service.product.ProductService;
import com.forerunner.core.service.product.PropertyService;
import com.forerunner.core.tool.CommUtil;
import com.forerunner.core.tool.ParamsTool;
import com.forerunner.core.web.resource.CommonParams;
import com.forerunner.foundation.domain.po.product.Brand;
import com.forerunner.foundation.domain.po.product.Classify;
import com.forerunner.foundation.domain.po.product.Product;
import com.forerunner.foundation.domain.po.product.Property;
import com.forerunner.web.controller.BaseController;
import com.google.common.collect.Maps;

/**
 * 产品后台管理
 * 包括(产品分类,产品品牌,产品规格管理)
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/admin/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ClassifyService classifyService;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private PropertyService propertyService;

	/**
	 * ajax获取产品分页信息
	 * 
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
		
		Product product = new Product();
		List<Product> resultList = productService.searchProduct(product, 0, 20);
		view.addObject("resultList", resultList);
		
		//自动封装分页信息
		return view;
	}

	/**
	 * 产品新增 进入页面之前检查是否有分类 品牌 规格信息 如果没有 则返回新增分类 品牌 规格页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add.htm")
	public ModelAndView addProduct(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/add");
		CommonParams.loadParams(view, "新增");
		Searchable sort=Searchable.newSearchable().addSort(Direction.DESC, "sort");
		Searchable sequence=Searchable.newSearchable().addSort(Direction.DESC, "sequence");
		// 品牌
		List<Brand> brandList=this.brandService.findAllWithSort(sequence);
		// 分类
		List<Classify> classifyList=this.classifyService.findAll();
		// 规格
		List<Property> propertyList=this.propertyService.findAllWithSort(sort); 
		view.addObject("brandList", brandList);
		view.addObject("classifyList", classifyList);
		view.addObject("propertyList", propertyList);
		
		return view;
	}

	/**
	 * 产品修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/edit.htm")
	public ModelAndView editProduct(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/edit");
		CommonParams.loadParams(view, "修改");
		return view;
	}

	/**
	 * 品牌列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/brandList.htm")
	public ModelAndView brandList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/brandList");
		CommonParams.loadParams(view, "品牌列表");
		Brand brand = new Brand();
		Page<Brand> page = brandService.searchBrand(brand, 0, 20);
		List<Brand> resultList = page.getContent();
		view.addObject("resultList", resultList);
		Integer numbers = page.getNumber();
		Integer totalPages = page.getTotalPages();
		Long totalElements = page.getTotalElements();
		view.addObject("numbers", numbers);
		view.addObject("totalpages", totalPages);
		view.addObject("totalElements", totalElements);
		return view;
	}

	/**
	 * 添加品牌
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addBrand.htm")
	public ModelAndView addBrand(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/brandAdd");
		CommonParams.loadParams(view, "产品列表");
		Product product = new Product();
		List<Product> list = productService.searchProduct(product, 0, 20);
		view.addObject("productList", list);
		return view;
	}

	/**
	 * 修改品牌
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editBrand.htm")
	public ModelAndView editBrand(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/brandAdd");
		Long id = CommUtil.null2Long(request.getParameter("id"));
		if (id == null || id < 1) {
			view.addObject("success", "修改失败!找不到该品牌.");
			view.setViewName("redirect:/admin/product/brandList.htm");
			return view;
		}
		Brand brand = this.brandService.findOne(id);
		if (brand == null) {
			view.addObject("success", "修改失败!找不到品牌,请刷新页面重试!");
			view.setViewName("redirect:/admin/product/brandList.htm");
			return view;
		}
		view.addObject("brand", brand);
		CommonParams.loadParams(view, brand.getName() + "修改");
		return view;
	}
	/**
	 * 新增或者修改品牌
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/brand/add.htm", method = RequestMethod.POST)
	public String addBrand(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		Brand brand = ParamsTool.WebRequestToBean(request, Brand.class);
		if (brand != null) {
			String str = "添加";
			if (brand.getId() != null && brand.getId() > 0) {
				str = "修改";
			}
			boolean flag = this.brandService.save(brand);
			if (flag) {
				attr.addFlashAttribute("title", brand.getName());
				attr.addFlashAttribute("success", str + "成功!");
				return "redirect:/admin/product/brandList.htm";
			}
			attr.addFlashAttribute("title", brand.getName());
			attr.addFlashAttribute("success", str + "失败!");
		}
		return "redirect:/admin/product/addBrand.htm";
	}
	/**
	 * 删除品牌
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/brand/delete.htm", method = RequestMethod.POST)
	public String deleteBrand(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		Long id = CommUtil.null2Long(request.getParameter("id"));
		boolean flag = this.brandService.delete(id);
		attr.addFlashAttribute("success", "删除失败!");
		if (flag) {
			attr.addFlashAttribute("success", "删除成功!");
		}
		return "redirect:/admin/product/brandList.htm";
	}

	/**
	 * 分类列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/classifyList.htm")
	public ModelAndView classifyList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/classifyList");
		CommonParams.loadParams(view, "产品分类列表");
		Classify classify = new Classify();
		Page<Classify> page = this.classifyService.searchClassify(classify, 0, 20);
		List<Classify> resultList = page.getContent();
		view.addObject("resultList", resultList);
		Integer numbers = page.getNumber();
		Integer totalPages = page.getTotalPages();
		Long totalElements = page.getTotalElements();
		view.addObject("numbers", numbers);
		view.addObject("totalpages", totalPages);
		view.addObject("totalElements", totalElements);
		return view;
	}

	/**
	 * 添加分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addClassify.htm")
	public ModelAndView addClassifyView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/classifyAdd");
		CommonParams.loadParams(view, "添加分类");
		return view;
	}

	/**
	 * 分类添加或者修改
	 * 
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/classify/add.htm", method = RequestMethod.POST)
	public String addClassify(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		Classify classify = ParamsTool.WebRequestToBean(request, Classify.class);
		if (classify != null) {
			String str = "添加";
			if (classify.getId() != null && classify.getId() > 0) {
				str = "修改";
			}
			boolean flag = this.classifyService.save(classify);
			if (flag) {
				attr.addFlashAttribute("title", classify.getTitle());
				attr.addFlashAttribute("success", str + "成功!");
				return "redirect:/admin/product/classifyList.htm";
			}
			attr.addFlashAttribute("title", classify.getTitle());
			attr.addFlashAttribute("success", str + "失败!");
		}
		return "redirect:/admin/product/addClassify.htm";
	}

	/**
	 * 删除分类
	 * 
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/classify/delete.htm", method = RequestMethod.POST)
	public String deleteClassify(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		Long id = CommUtil.null2Long(request.getParameter("id"));
		boolean flag = this.classifyService.delete(id);
		attr.addFlashAttribute("success", "删除失败!");
		if (flag) {
			attr.addFlashAttribute("success", "删除成功!");
		}
		return "redirect:/admin/product/classifyList.htm";
	}

	/**
	 * 修改分类
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editClassify.htm", method = RequestMethod.POST)
	public ModelAndView editClassify(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/classifyAdd");
		Long id = CommUtil.null2Long(request.getParameter("id"));
		if (id == null || id < 1) {
			view.addObject("success", "添加失败!找不到该分类.");
			view.setViewName("redirect:/admin/product/classifyList.htm");
			return view;
		}
		Classify classify = this.classifyService.findOne(id);
		if (classify == null) {
			view.addObject("success", "添加失败!找不到分类,请刷新页面重试!");
			view.setViewName("redirect:/admin/product/classifyList.htm");
			return view;
		}
		view.addObject("classify", classify);
		CommonParams.loadParams(view, classify.getTitle() + "修改");
		return view;
	}

	/**
	 * 规格列表 只可以新增 不可修改和删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/propertyList.htm")
	public ModelAndView propertyList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/propertyList");
		CommonParams.loadParams(view, "规格属性");
		Property property=new Property();
		Page<Property> page=this.propertyService.searchProperty(property, 0, 20);
		List<Property> resultList = page.getContent();
		view.addObject("resultList", resultList);
		Integer numbers = page.getNumber();
		Integer totalPages = page.getTotalPages();
		Long totalElements = page.getTotalElements();
		view.addObject("numbers", numbers);
		view.addObject("totalpages", totalPages);
		view.addObject("totalElements", totalElements);
		return view;
	}
	/**
	 * 添加品牌
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addProperty.htm")
	public ModelAndView addProperty(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/propertyAdd");
		CommonParams.loadParams(view, "规格属性");
		return view;
	}

	/**
	 * 修改品牌
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editProperty.htm")
	public ModelAndView editProperty(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/product/propertyAdd");
		Long id = CommUtil.null2Long(request.getParameter("id"));
		if (id == null || id < 1) {
			view.addObject("success", "修改失败!找不到该规格.");
			view.setViewName("redirect:/admin/product/propertyList.htm");
			return view;
		}
		Property property = this.propertyService.findOne(id);
		if (property == null) {
			view.addObject("success", "修改失败!找不到该规格,请刷新页面重试!");
			view.setViewName("redirect:/admin/product/propertyList.htm");
			return view;
		}
		view.addObject("property", property);
		CommonParams.loadParams(view, property.getPropertyName() + "修改");
		return view;
	}
	/**
	 * 新增或者修改品牌
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/property/add.htm", method = RequestMethod.POST)
	public String addProperty(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		Property property = ParamsTool.WebRequestToBean(request, Property.class);
		if (property != null) {
			String str = "添加";
			if (property.getId() != null && property.getId() > 0) {
				str = "修改";
			}
			boolean flag = this.propertyService.save(property);
			if (flag) {
				attr.addFlashAttribute("title", property.getPropertyName());
				attr.addFlashAttribute("success", str + "成功!");
				return "redirect:/admin/product/propertyList.htm";
			}
			attr.addFlashAttribute("title",  property.getPropertyName());
			attr.addFlashAttribute("success", str + "失败!");
		}
		return "redirect:/admin/product/addProperty.htm";
	}
	/**
	 * 删除品牌
	 * @param request
	 * @param response
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/property/delete.htm", method = RequestMethod.POST)
	public String deleteProperty(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) {
		Long id = CommUtil.null2Long(request.getParameter("id"));
		boolean flag = this.propertyService.delete(id);
		attr.addFlashAttribute("success", "删除失败!");
		if (flag) {
			attr.addFlashAttribute("success", "删除成功!");
		}
		return "redirect:/admin/product/propertyList.htm";
	}

}
