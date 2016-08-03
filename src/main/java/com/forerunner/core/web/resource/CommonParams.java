package com.forerunner.core.web.resource;


import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.forerunner.core.service.system.ConfigService;
import com.forerunner.core.tool.SpringUtils;
import com.forerunner.foundation.domain.po.system.Config;

/**
 * 获取页面的公共参数
 * @author wx
 *
 */
public class CommonParams {
	
	private static Logger log=Logger.getLogger(CommonParams.class);
	/**
	 * 获取页面的公共参数 <br>
	 * title格式： 公司名称 | title
	 * @param view
	 * @param title
	 */
	public static void loadParams(ModelAndView view,String title){
		if(view==null){
			return;
		}
		ConfigService config=SpringUtils.getBean("configService");
		List<Config> list=config.getSystemConfig("system");
		PageParams page=listToBean(list);
		String title1=title;
		if(StringUtils.isNotBlank(page.getCorporateName())){
			if(StringUtils.isNotBlank(title)){
				title1=page.getCorporateName()+" | "+title;
			}else{
				title1=page.getCorporateName();
			}
		}
		view.addObject("config", page);
		view.addObject("title", title1);
		//获取友情链接(缓存)
		//获取头部菜单(缓存)
		//获取滚动推荐(缓存)
	}
	
	public static void loadParams(ModelAndView view){
		loadParams(view,"");
	}
	
	/**
	 * List转换成bean
	 * @param list
	 * @return
	 */
	private static PageParams listToBean(List<Config> list){
		PageParams page=new PageParams();
		if(list==null){
			return page;
		}
		for(Config con:list){
			String type=con.getType();
			Class<PageParams> clazz=PageParams.class;
			Field[] fields=clazz.getDeclaredFields();
			for(Field field:fields){
				String name=field.getName();
				if(name.equalsIgnoreCase(type)){
					field.setAccessible(true);
					String fieldType=field.getType().toString();
					if(fieldType.endsWith("String")){
						try {
							field.set(page, con.getValue());
						} catch (IllegalArgumentException | IllegalAccessException e) {
							log.error("注入值错误，Error："+e.getMessage());
						}
					}
					break;
				}
			}			
		}
		return page;
	}
	
}
