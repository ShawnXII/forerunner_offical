package com.forerunner.core.tool;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.forerunner.core.enumeration.FileUse;
import com.forerunner.foundation.domain.po.system.Account;

/**
 * 图片上传
 * @author Administrator
 *
 */
public class FileUpload  {
	/***
	 * 上传文件的根目录
	 */
	 private static final String ROOT_FILE_PATH = "/upload";
	 
	 private static final String SYSTEM_FILE_PATH="/system";
	 
	 
	public static String uploadFile(MultipartFile file, HttpServletRequest request,FileUse use) throws IOException{
		String fileName = file.getOriginalFilename();
		//判断是否已经登录 如果登录 则上传至登录ID的文件夹 如/upload/用户ID/用途/日期/文件名称
		Account acc=LoginUtil.getAccount(request);
		StringBuilder filePath=new StringBuilder(ROOT_FILE_PATH);
		if(acc!=null){
			filePath.append("/"+acc.getId());
		}else{
			filePath.append(SYSTEM_FILE_PATH);
		}
		String date=CommUtil.formatDate("yyyyMM");
		filePath.append("/").append(date);
		if(use!=null){
			filePath.append("/"+use.name());
		}
		File tempFile=new File(filePath.toString(),String.valueOf(fileName));
		if(!tempFile.getParentFile().exists()){
			tempFile.getParentFile().mkdirs();
		}
		if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }
		file.transferTo(tempFile);
		//File tempFile = new File(FILE_PATH, new Date().getTime() + String.valueOf(fileName));
		return "/download?fileName=" + tempFile.getName();
	}
	
}
