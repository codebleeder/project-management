package com.jrp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.IUserAccountRepository;
import com.jrp.pma.entities.UserAccount;

@Service
public class UserAccountService {
	
	
	@Autowired
	IUserAccountRepository userAccountRepo;
	
	public UserAccount save(UserAccount user) {
		return userAccountRepo.save(user);
	}
	
	public List<UserAccount> getAll(){
		return userAccountRepo.findAll();
	}
		
}
