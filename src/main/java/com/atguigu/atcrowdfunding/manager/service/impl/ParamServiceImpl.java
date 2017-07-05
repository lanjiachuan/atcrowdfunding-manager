package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.bean.Param;
import com.atguigu.atcrowdfunding.manager.dao.ParamDao;
import com.atguigu.atcrowdfunding.manager.service.ParamService;

@Service
public class ParamServiceImpl implements ParamService {
	
	@Autowired
	private ParamDao paramDao;
	
	public List<Param> paramQuery(Map<String, Object> paraMap) {
		return paramDao.paramQuery(paraMap);
	}

	public int paramUpdateVal(Map<String, Object> paraMap) {
		return paramDao.paramUpdateVal(paraMap);
	}


}
