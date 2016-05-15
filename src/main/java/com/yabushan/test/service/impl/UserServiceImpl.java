package com.yabushan.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yabushan.test.dao.IUserDao;
import com.yabushan.test.pojo.UserInfo;
import com.yabushan.test.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	public UserInfo getUserById(int userId) {
		return this.userDao.selectByPrimaryKeys(userId);
	}

}
