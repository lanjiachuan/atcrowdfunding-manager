package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Type;
import com.atguigu.atcrowdfunding.manager.dao.TypeDao;
import com.atguigu.atcrowdfunding.manager.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeDao typeDao;
	
	public List<Cert> queryAllCerts() {
		return typeDao.queryAllCerts();
	}

	public List<Type> queryAllTypes() {
		return typeDao.queryAllTypes();
	}

	public void insertAcctTypeCert(Map<String,Object> paramMap) {
		typeDao.insertAcctTypeCert(paramMap);
	}

	public void deleteAcctTypeCert(Map<String, Object> paramMap) {
		typeDao.deleteAcctTypeCert(paramMap);
	}

	public List<Map<String, Object>> queryAllTypeCert() {
		return typeDao.queryAllTypeCert();
	}

}
