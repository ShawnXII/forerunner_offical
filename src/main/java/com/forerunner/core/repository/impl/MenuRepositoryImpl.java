package com.forerunner.core.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.forerunner.core.tool.CommUtil;
import com.forerunner.foundation.domain.po.system.Menu;
import com.google.common.collect.Lists;

public class MenuRepositoryImpl {
	@PersistenceContext
	private EntityManager em;
	
	public List<Menu> gainMenu(){
		List<Menu> result=Lists.newArrayList();
		//先查询表里面最大的level
		String sql="select max(level) from sys_menu where deleteStatus=?";
		Query query=em.createNativeQuery(sql);
		query.setParameter(1, Boolean.FALSE);
		Integer maxLevel=CommUtil.null2Int(query.getSingleResult(), -1);
		if(maxLevel==-1){
			return result;
		}
		//再从0级开始查询一直到最大level级别的表 左查询
		StringBuilder sb=new StringBuilder("select ");
		for(int i=1;i<=(maxLevel+1);i++){
			sb.append("");
		}
		sb.append(" from ");
		for(int i=1;i<=(maxLevel+1);i++){
			String alias="menu"+i;
			if(i>1){
				sb.append(" left join ");
			}
			sb.append(" sys_menu as ").append(alias);
			if(i>1){
				String parentAlias="menu"+(i-1);
				sb.append(" on ( ").append(parentAlias).append(".id = ").append(alias).append(".parent_id )");
			}
		}
		return null;
	}
}
