package com.atguigu.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Cert;
import com.atguigu.atcrowdfunding.common.bean.Type;
import com.atguigu.atcrowdfunding.manager.service.TypeService;

@Controller
@RequestMapping(value="type")
public class TypeController {

	
	@Autowired
	private TypeService typeService;
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String type(Model model) {
		
		List<Cert> certs =  typeService.queryAllCerts();
		List<Type> types =  typeService.queryAllTypes();
		List<Map<String,Object>> maps = typeService.queryAllTypeCert();
		
		model.addAttribute("certs", certs);
		model.addAttribute("types", types);
		model.addAttribute("maps", maps);
		
		
		return "type/type";

	}
	
	@ResponseBody
	@RequestMapping(value="insertAcctTypeCert")
	public Object insertAcctTypeCert(String accttype ,Integer certid){
		AJAXResult result = new AJAXResult();
		
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("accttype", accttype);
			paramMap.put("certid", certid);
			typeService.insertAcctTypeCert(paramMap);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="deleteAcctTypeCert")
	public Object deleteAcctTypeCert(String accttype ,Integer certid){
		AJAXResult result = new AJAXResult();
		
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("accttype", accttype);
			paramMap.put("certid", certid);
			typeService.deleteAcctTypeCert(paramMap);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
}
