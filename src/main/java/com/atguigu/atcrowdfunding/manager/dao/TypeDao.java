package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Type;

public interface TypeDao {

	public List<Cert> queryAllCerts();

	public List<Type> queryAllTypes();

	public void insertAcctTypeCert(Map<String,Object> paramMap);

	public void deleteAcctTypeCert(Map<String, Object> paramMap);

	public List<Map<String, Object>> queryAllTypeCert();

}
