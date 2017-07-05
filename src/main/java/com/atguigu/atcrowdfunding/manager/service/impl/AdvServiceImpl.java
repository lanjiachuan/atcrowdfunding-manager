package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.bean.Advertisement;
import com.atguigu.atcrowdfunding.common.bean.DatasAdv;
import com.atguigu.atcrowdfunding.manager.dao.AdvDao;
import com.atguigu.atcrowdfunding.manager.service.AdvService;

@Service
public class AdvServiceImpl implements AdvService {

	@Autowired
	private AdvDao advDao;

	public List<Advertisement> advPageQuery(Map<String, Object> paraMap) {
		return advDao.advPageQuery(paraMap);
	}

	public int advQueryCount(Map<String, Object> paraMap) {
		return advDao.advQueryCount(paraMap);
	}

	public Advertisement queryAdvById(Integer id) {
		return advDao.queryAdvById(id);
	}

	public int updateAdv(Advertisement adv) {
		return advDao.updateAdv(adv);
	}

	public int insertAdv(Advertisement adv) {
		return advDao.insertAdv(adv);
	}

	public int deleteAdv(Integer id) {
		return advDao.deleteAdv(id);
	}

	public int deleteAdvs(DatasAdv ds) {
		return advDao.deleteAdvs(ds);
	}

	public void inertAdv(Advertisement adv) {
		advDao.inertAdv(adv);
	}
}
