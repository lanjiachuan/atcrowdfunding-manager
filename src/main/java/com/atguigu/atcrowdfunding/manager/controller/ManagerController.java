package com.atguigu.atcrowdfunding.manager.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.common.bean.AJAXResult;
import com.atguigu.atcrowdfunding.common.bean.Advertisement;
import com.atguigu.atcrowdfunding.common.bean.DatasAdv;
import com.atguigu.atcrowdfunding.common.bean.Page;
import com.atguigu.atcrowdfunding.common.bean.Param;
import com.atguigu.atcrowdfunding.common.bean.User;
import com.atguigu.atcrowdfunding.manager.service.AdvService;
import com.atguigu.atcrowdfunding.manager.service.ParamService;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private AdvService advService;
	@Autowired
	private ParamService paramService;
	
	
	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/paramUpdateVal")
	public Object paramUpdateVal(Integer id,Integer val) {
		AJAXResult result = new AJAXResult();
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", id);
			paraMap.put("val", val);
			int i = paramService.paramUpdateVal(paraMap);
			if (i == 1) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteAdv")
	public Object deleteAdv(Integer id) {
		AJAXResult result = new AJAXResult();
		try {
			int i = advService.deleteAdv(id);
			if (i == 1) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteAdvs")
	public Object deletes(DatasAdv ds) {
		AJAXResult result = new AJAXResult();
		try {
			int i = advService.deleteAdvs(ds);
			if (i != 0) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	

	/**
	 * 后台保存用户
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/advInsert")
	public Object advInsert(HttpServletRequest req,HttpSession session,Advertisement adv) {
		AJAXResult result = new AJAXResult();

		try {
			MultipartHttpServletRequest request =
					(MultipartHttpServletRequest)req;
			//根据文件域的name属性获取文件
			MultipartFile file = request.getFile("advPic");
			//获取文件的文件名
			String realname = file.getOriginalFilename();
			//文件的上传名
			String filename = UUID.randomUUID().toString() + realname.substring(realname.lastIndexOf("."));
			
			String realPath = session.getServletContext().getRealPath("pics");
			
			//将文件拷贝至本地
			File destFile = new File(realPath+"/adv/"+ filename);
			
			file.transferTo(destFile);
			
			User loginUser = (User) session.getAttribute("loginUser");
			
			adv.setStatus(1);
			adv.setIconpath(filename);
			adv.setUserid(loginUser.getId());
			advService.inertAdv(adv);
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}

	@ResponseBody
	@RequestMapping("/advUpdate")
	public Object advUpdate(Advertisement adv) {
		AJAXResult result = new AJAXResult();

		try {
			int i = advService.updateAdv(adv);
			if (i == 1) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 跳转到广告advAdd
	 * 
	 * @return
	 */
	@RequestMapping("/advAdd")
	public String advAdd(Model model) {

		return "manager/advAdd";
	}

	/**
	 * 跳转到广告edit
	 * 
	 * @return
	 */
	@RequestMapping("/advEdit")
	public String advEdit(Integer id, Model model) {
		Advertisement adv = advService.queryAdvById(id);
		model.addAttribute("adv", adv);
		return "manager/edit";
	}

	/**
	 * 分页查询广告（包括模糊查询）
	 * 
	 * @param pageText
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/advPageQuery")
	public Object advPageQuery(String pageText, Integer pageNo, Integer pageSize) {

		AJAXResult result = new AJAXResult();

		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			
			if ( StringUtil.isNotEmpty(pageText) ) {
				pageText = pageText.replaceAll("%", "");
				pageText = pageText.replaceAll("_", "");
				
			}

			
			paraMap.put("start", (pageNo - 1) * pageSize);
			paraMap.put("size", pageSize);
			paraMap.put("pageText", pageText);
			// 分页查询数据
			List<Advertisement> adv = advService.advPageQuery(paraMap);
			// 获取数据的总条数
			int acount = advService.advQueryCount(paraMap);
			int totalNo = 0;
			if (acount % pageSize == 0) {
				totalNo = acount / pageSize;
			} else {
				totalNo = acount / pageSize + 1;
			}

			Page<Advertisement> advPage = new Page<Advertisement>();

			advPage.setDatas(adv);
			advPage.setPageNo(pageNo);
			advPage.setPageSize(pageSize);
			advPage.setTotalNo(totalNo);
			advPage.setTotalSize(acount);

			result.setPageObj(advPage);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}
	
	/**
	 * 查询参数（包括模糊查询）
	 * 
	 * @param pageText
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/paramQuery")
	public Object paramQuery(String pageText) {

		AJAXResult result = new AJAXResult();

		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("pageText", pageText);
			// 分页查询数据
			List<Param> param = paramService.paramQuery(paraMap);
			// 获取数据的总条数
			Page<Param> paramPage = new Page<Param>();

			paramPage.setDatas(param);

			result.setPageObj(paramPage);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}
	
	

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/advertisement")
	public String advertisement() {
		return "manager/advertisement";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/role")
	public String role() {

		return "manager/role";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/permission")
	public String permission() {

		return "manager/permission";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/auth_cert")
	public String auth_cert() {

		return "manager/auth_cert";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/auth_adv")
	public String auth_adv() {

		return "manager/auth_adv";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/auth_project")
	public String auth_project() {

		return "manager/auth_project";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/cert")
	public String cert() {
		return "manager/cert";

	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/process")
	public String process() {
		return "manager/process";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/message")
	public String message() {
		return "manager/message";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/project_type")
	public String project_type() {
		return "manager/project_type";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/tag")
	public String tag() {
		return "manager/tag";

	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/param")
	public String param() {
		return "manager/param";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/assignPermission")
	public String assignPermission() {
		return "manager/assignPermission";
	}

}
