package com.yuki.account.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuki.account.dto.User;
import com.yuki.account.service.UserService;



@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	@Resource
	UserService userService;
	
	@Resource
	HttpServletRequest request;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(ModelMap model,@RequestParam String username,@RequestParam String password){
		logger.info(username+":"+password);

		User user = userService.checkUser(username, password);
		if (user!=null) {
			request.getSession().setAttribute("loginUser",user );
			logger.info("--登陆成功--");
			return "main";
		}else {
			logger.info("--登陆失败--");
		}
		return "err";
	}
	
}
