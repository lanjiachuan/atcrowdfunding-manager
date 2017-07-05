package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.bean.Ticket;
import com.atguigu.atcrowdfunding.manager.dao.AuthCertDao;
import com.atguigu.atcrowdfunding.manager.service.AuthCertService;

@Service
public class AuthCertServiceImpl implements AuthCertService {

	@Autowired
	private AuthCertDao authCertDao;
	
	public List<Ticket> queryAllTicket() {
		
		return authCertDao.queryAllTicket();
	}

}
