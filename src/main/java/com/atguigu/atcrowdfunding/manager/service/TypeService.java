package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Type;

public interface TypeService {

	List<Cert> queryAllCerts();

	List<Type> queryAllTypes();

	void insertAcctTypeCert(Map<String,Object> paramMap);

	void deleteAcctTypeCert(Map<String, Object> paramMap);

	List<Map<String, Object>> queryAllTypeCert();

}
