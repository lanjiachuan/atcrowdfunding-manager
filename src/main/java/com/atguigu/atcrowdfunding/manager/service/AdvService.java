package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Advertisement;
import com.atguigu.atcrowdfunding.common.bean.DatasAdv;

public interface AdvService {

	List<Advertisement> advPageQuery(Map<String, Object> paraMap);

	int advQueryCount(Map<String, Object> paraMap);

	Advertisement queryAdvById(Integer id);

	int updateAdv(Advertisement adv);

	int insertAdv(Advertisement adv);

	int deleteAdv(Integer id);

	int deleteAdvs(DatasAdv ds);

	void inertAdv(Advertisement adv);

}
