package com.forerunner.core.service.system;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.system.Config;

@Service
public class ConfigService extends BaseService<Config, Long>{
	
	public List<Config> getSystemConfig(final String classify){
		Specification<Config> spec=new Specification<Config>(){
			@Override
			public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.get("");
				
				return null;
			}		
		};
		return null;
	}
}
