package com.yabushan.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.util.logging.resources.logging;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yabushan.test.pojo.UserInfo;
import com.yabushan.test.service.IUserService;

@Controller
@RemoteProxy(name="dwrService")
public class DwrUserController {
	
	@Resource
	private IUserService userService;
	Logger logger=Logger.getLogger(DwrUserController.class);
	
	public String toIndex(HttpServletRequest request,Model model){
		int userId=Integer.parseInt(request.getParameter("id"));
		UserInfo userInfo=this.userService.getUserById(userId);
		model.addAttribute("user",userInfo);
		logger.info(userInfo.toString());
		return "showUser";
	}
	
	@RemoteMethod
	public String sayHello(String string){
		UserInfo userInfo=this.userService.getUserById(1);
		return "你好！"+JSON.toJSONString(userInfo)+string;
		
	}
	

}
