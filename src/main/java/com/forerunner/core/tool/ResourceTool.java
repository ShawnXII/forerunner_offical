package com.forerunner.core.tool;

import com.forerunner.core.service.system.ConfigService;

/**
 * 公共资源获取 
 * 如 网站的顶部菜单
 * 公司名称
 * 网站Title 作者 关键字 等
 * @author Administrator
 *
 */
public class ResourceTool {
	
	
	public void getConfig(){
		ConfigService configService=SpringUtils.getBean(ConfigService.class);
		configService.getSystemConfig("");
		
	}
}
