package com.najoon.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.najoon.study.model.UserVO;
import com.najoon.study.persistance.UserDao;
import com.najoon.study.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	@Override
	public UserVO findById(String u_seq) {
		// TODO Auto-generated method stub
		return userDao.findById(u_seq);
	}

	@Override
	public UserVO findByName(String u_username) {
		// TODO Auto-generated method stub
		return userDao.findByName(u_username);
	}
	
	@Override
	public int insert(UserVO userVO) {
		// TODO Auto-generated method stub
		userDao.insert(userVO);
		return 0;
	}

	@Override
	public int update(UserVO userVO) {
		// TODO Auto-generated method stub
		return userDao.update(userVO);
	}

	@Override
	public int delete(String u_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create_user_table() {

	}

}
