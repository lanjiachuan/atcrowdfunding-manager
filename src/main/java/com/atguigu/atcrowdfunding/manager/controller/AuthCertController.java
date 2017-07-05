package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Page;


@Controller
@RequestMapping(value="auth_cert")
public class AuthCertController {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="index")
	public String index(){
		
		
		
		return "auth/index";
	}
	
	@ResponseBody
	@RequestMapping(value="queryAllTicket")
	public Object queryAllTicket(Integer pageNo,Integer pageSize){
		AJAXResult result = new AJAXResult();
		
		try {
			
			//创建任务查询
			TaskQuery query = taskService.createTaskQuery();
			List<Task> tasks = query
				.taskCandidateGroup("backuser")
					.listPage((pageNo-1)*pageSize, pageSize);
			
			//总记录数
			int count = (int)query.count();
			
			int totalNo = 0;
			if(count%pageSize == 0){
				totalNo = count/pageSize;
			}else{
				totalNo = count/pageSize + 1;
			}
			
//			Page page = result.getPageObj();
//			
//			page.setDatas(tasks);
//			page.setPageNo(pageNo);
//			page.setPageSize(pageSize);
//			page.setTotalNo(totalNo);
//			page.setTotalSize(count);
// 				
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		result.setSuccess(true);
		return result;
	}
}
