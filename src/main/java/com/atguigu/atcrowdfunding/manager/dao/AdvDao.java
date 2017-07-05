package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.bean.Advertisement;
import com.atguigu.atcrowdfunding.common.bean.DatasAdv;

public interface AdvDao {

	public List<Advertisement> advPageQuery(Map<String, Object> paraMap);

	public int advQueryCount(Map<String, Object> paraMap);

	public Advertisement queryAdvById(Integer id);

	public int updateAdv(Advertisement adv);

	public int insertAdv(Advertisement adv);

	public int deleteAdvs(DatasAdv ds);

	public int deleteAdv(Integer id);

	public void inertAdv(Advertisement adv);

}
