package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;

import com.atguigu.atcrowdfunding.common.bean.Ticket;

public interface AuthCertDao {

	public List<Ticket> queryAllTicket();
}
