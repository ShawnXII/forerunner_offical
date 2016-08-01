package com.forerunner.core.repository.system;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.forerunner.core.repository.BaseRepository;
import com.forerunner.foundation.domain.po.system.Account;

public interface AccountRepository extends BaseRepository<Account,Long>{
	
	@Query("select acc from Account acc where acc.username=?1 or acc.email=?1  or acc.mobile=?1 ")
	public List<Account> getAccountByUsername(String username);
}
