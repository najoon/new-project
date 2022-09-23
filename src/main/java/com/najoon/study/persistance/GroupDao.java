package com.najoon.study.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.najoon.study.model.GroupVO;

public interface GroupDao {
	
	public List<GroupVO> selectAll();
	public GroupVO findByGroup(long g_seq);
	public List<GroupVO> findByGroupPeople(String g_name);
	public GroupVO findByOnePeople(@Param("j_gname") String j_gname,@Param("j_username") String j_username);
	public int insert(GroupVO groupVO);
	public int insertPeople(GroupVO groupVO);
	public int updateGroupIn(GroupVO groupVO);
	public int updateUser(GroupVO groupVO);
	public int delete(long g_seq);
	public int deletePeople(long j_seq);
	
	public void create_group_table();
	public void create_join_group_table();
}
