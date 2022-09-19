package com.heavenstar.zandi.persistance;

import java.util.List;

import com.heavenstar.zandi.model.UserVO;

public interface UserDao {
	
	public List<UserVO> selectAll();
	public UserVO findById(String u_seq);
	public UserVO findByName(String u_username);
	public int insert(UserVO userVO);
	public int update(UserVO userVO);
	public int delete(String u_seq);
	
	public void create_user_table();

}
