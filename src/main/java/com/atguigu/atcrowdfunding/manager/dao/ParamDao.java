package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Param;

public interface ParamDao {

	List<Param> paramQuery(Map<String, Object> paraMap);

	int paramUpdateVal(Map<String, Object> paraMap);

}
