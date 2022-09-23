package com.najoon.study.persistance;

import java.util.List;

import com.najoon.study.model.UserVO;

public interface UserDao {
	
	public List<UserVO> selectAll();
	public UserVO findById(String u_seq);
	public UserVO findByName(String u_username);
	public int insert(UserVO userVO);
	public int update(UserVO userVO);
	public int delete(String u_seq);
	
	public void create_user_table();

}
