package com.forerunner.core.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forerunner.core.repository.system.AccountRepository;
import com.forerunner.core.service.BaseService;
import com.forerunner.foundation.domain.po.system.Account;

@Service
public class AccountService extends BaseService<Account, Long>{
	
	@Autowired
	private AccountRepository accountRepository;
	/**
	 * 根据用户名/邮箱/手机 找到帐号
	 * @param username
	 * @return
	 */
	public Account getAccountByUsername(String username){
		List<Account> list=accountRepository.getAccountByUsername(username);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

}
