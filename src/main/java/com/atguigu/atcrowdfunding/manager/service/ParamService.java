package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Param;

public interface ParamService {

	List<Param> paramQuery(Map<String, Object> paraMap);


	int paramUpdateVal(Map<String, Object> paraMap);

}
