package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Page;

@Controller
@RequestMapping(value="process")
public class ProcessController {

	//自动装配流程定义对象
	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping(value="/index")
	public String index(){
		
		return "process/index";
	}
	
	
	@RequestMapping(value="showprocess")
	public String showprocess(){
		
		return "process/showprocess";
	}
	
	
	/**
	 * 上传流程定义文件
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="uploadProcDefFile")
	public Object uploadProcDefFile( HttpServletRequest req ){
		AJAXResult result = new AJAXResult();
		
		try {
			
			MultipartHttpServletRequest  request= 
					(MultipartHttpServletRequest)req;
			
			MultipartFile file = request.getFile("procDefFile");
			
			//完成流程部署
			repositoryService.createDeployment()
				.addInputStream(file.getOriginalFilename(), file.getInputStream())
				.deploy();
			
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping(value="deleteProcess")
	public Object deleteProcess(){
		AJAXResult result = new AJAXResult();
		
		
		
		return result;
	}
	
	/**
	 * 分页查询流程定义文件
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="pageQuery")
	public Object pageQuery(Integer pageNo,Integer pageSize ){
		AJAXResult result = new AJAXResult();
		
		try {
			//查询流程定义数据
			
			ProcessDefinitionQuery query = 
					repositoryService.createProcessDefinitionQuery();
			
			//利用流程定义自带的分页查询方法查询
			List<ProcessDefinition> pds = query.listPage((pageNo - 1)* pageSize, pageSize);
			
			List<Map<String,Object>> pdMaps = new ArrayList<Map<String,Object>>();
			for (ProcessDefinition pd : pds) {
				//将每一个流程实例的name,key和version放入到map集合中,
				Map<String,Object> pdMap = new HashMap<String,Object>();
				pdMap.put("id",pd.getId() );
				pdMap.put("name",pd.getName() );
				pdMap.put("key", pd.getKey());
				pdMap.put("version",pd.getVersion() );
				pdMaps.add(pdMap);
			}
			
			int acount = (int)query.count();
			int totalNo = 0;
			if (acount % pageSize == 0) {
				totalNo = acount / pageSize;
			} else {
				totalNo = acount / pageSize + 1;
			}

			//上面的是流程数据
			//这个是分页数据
			Page<Map<String,Object>> pdPage = new Page<Map<String,Object>>();

			//这个是将流程数据放到分页中,原来可以放对象,但是流程数据中存在对象关联的问题,用map可以解决
			pdPage.setDatas(pdMaps);
			
			pdPage.setPageNo(pageNo);
			pdPage.setPageSize(pageSize);
			pdPage.setTotalNo(totalNo);
			pdPage.setTotalSize(acount);

			result.setPageObj(pdPage);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
