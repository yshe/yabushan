package com.yabushan.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yabushan.test.pojo.UserInfo;
import com.yabushan.test.service.IUserService;
import com.yabushan.test.util.common.RestRspVO;

@RestController
@RequestMapping("/userInfo")
public class RestUserController {
	
	@Resource
	private IUserService userService;
	
	Logger logger=Logger.getLogger(RestUserController.class);
	
	@RequestMapping("/showUser")
	public RestRspVO<UserInfo> toIndex(HttpServletRequest request,Model model){
		RestRspVO<UserInfo> result=new RestRspVO<UserInfo>();
		result.setCode(1);
		result.setMessage("成功！");
		int userId=Integer.parseInt(request.getParameter("id"));
		UserInfo userInfo=this.userService.getUserById(userId);
		result.setResult(userInfo);
		return result;
		
	}
	

}
