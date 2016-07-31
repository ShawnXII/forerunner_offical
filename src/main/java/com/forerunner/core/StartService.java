package com.forerunner.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.forerunner.core.service.product.ProductService;
import com.forerunner.core.service.system.ConfigService;

public class StartService {
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-configuration.xml");
		ConfigService service=ctx.getBean(ConfigService.class);
		System.out.println(service.findOne(1L));
	}
}
