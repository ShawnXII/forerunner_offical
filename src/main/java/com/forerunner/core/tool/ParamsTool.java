package com.forerunner.core.tool;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

public class ParamsTool {
	
	private static final Logger logger=Logger.getLogger(ParamsTool.class);
	
	public static <T> T WebRequestToBean(HttpServletRequest request, Class<T> clazz) {
		T obj=null;
		Map<String, String> map = WebRequestToMap(request);
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			obj = clazz.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : propertyDescriptors) {
				String propertyName = pd.getName();		
				if (map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					if(value==null){
						continue;
					}
					try {
						BeanUtils.setProperty(obj, propertyName, value);
					} catch (InvocationTargetException e) {
					
					}	
				}
			}
		} catch (IntrospectionException | InstantiationException | IllegalAccessException e) {
			logger.error(e);
		}
		return obj;
	}
	
	/*private static void invoke(Method method){
		
	}*/

	public static Map<String, String> WebRequestToMap(HttpServletRequest request) {
		Map<String, String> result = Maps.newHashMap();
		Map<String, String[]> map = request.getParameterMap();
		if (map != null) {
			Set<Entry<String, String[]>> keSet = map.entrySet();
			for (Iterator<Entry<String, String[]>> itr = keSet.iterator(); itr.hasNext();) {
				Entry<String, String[]> entry = itr.next();
				String[] value = entry.getValue();
				if (value == null || value.length <= 0) {
					continue;
				}
				String val = "";
				for (String str : value) {
					if (StringUtils.isNotBlank(str)) {
						val = str;
						continue;
					}
				}
				result.put(entry.getKey(), val);
			}
		}
		return result;
	}
}
