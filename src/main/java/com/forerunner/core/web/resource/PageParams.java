package com.forerunner.core.web.resource;
/**
 * 页面参数
 * @author wx
 *
 */
public class PageParams {
	//页面描述
	private String description;
	//公司名称
	private String corporateName;
	//作者
	private String author;
	//SEO
	private String keyWords;
	//图标
	private String favicon;
	//INC
	private String copyright;
	//图片服务器地址
	private String imagePath;
	//项目地址
	private String contextPath;
	//静态资源地址
	private String resourcePath;
	
	private String webLogo;
	
	
	public PageParams(){
		super();
		this.description="";
		this.corporateName="欧沃泉";
		this.author="ShawnXII";
		this.favicon="/media/image/favicon/favicon.ico";
		this.copyright="2016 &copy; Official - "+this.corporateName+" 官网后台.";
		this.imagePath="/static";
		this.contextPath="/official";
		this.resourcePath="/official/static";
	}
	
	
	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getContextPath() {
		return contextPath;
	}


	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getFavicon() {
		return favicon;
	}
	public void setFavicon(String favicon) {
		this.favicon = favicon;
	}

	public String getCopyright() {
		return copyright;
	}


	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}


	public String getResourcePath() {
		return resourcePath;
	}


	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}


	@Override
	public String toString() {
		return "PageParams [description=" + description + ", corporateName=" + corporateName + ", author=" + author
				+ ", keyWords=" + keyWords + ", favicon=" + favicon + ", copyright=" + this.copyright + ", imagePath=" + imagePath
				+ ", contextPath=" + contextPath + "]";
	}


	public String getWebLogo() {
		return webLogo;
	}


	public void setWebLogo(String webLogo) {
		this.webLogo = webLogo;
	}
	
		
}
