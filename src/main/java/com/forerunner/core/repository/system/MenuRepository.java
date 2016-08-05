package com.forerunner.core.repository.system;

import java.util.List;

import com.forerunner.core.repository.BaseRepository;
import com.forerunner.foundation.domain.po.system.Menu;

public interface MenuRepository extends BaseRepository<Menu,Long>{
	
	public List<Menu> gainMenu();
}	
