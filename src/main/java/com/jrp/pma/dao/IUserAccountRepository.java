package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.entities.UserAccount;

public interface IUserAccountRepository extends CrudRepository<UserAccount, Long>{
	@Override
	public List<UserAccount> findAll();
}
