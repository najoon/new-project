package com.heavenstar.zandi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenstar.zandi.model.GroupVO;
import com.heavenstar.zandi.persistance.GroupDao;
import com.heavenstar.zandi.service.GroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupDao groupDao;

	@Override
	public List<GroupVO> selectAll() {
		// TODO Auto-generated method stub
		return groupDao.selectAll();
	}

	@Override
	public GroupVO findByGroup(long g_seq) {
		// TODO Auto-generated method stub
		return groupDao.findByGroup(g_seq);
	}
	
	@Override
	public List<GroupVO> findByGroupPeople(String g_name) {
		// TODO Auto-generated method stub
		return groupDao.findByGroupPeople(g_name);
	}
	
	@Override
	public GroupVO findByOnePeople(String j_gname, String j_username) {
		// TODO Auto-generated method stub
		return groupDao.findByOnePeople(j_gname, j_username);
	}

	@Override
	public int insert(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.insert(groupVO);
	}

	@Override
	public int insertPeople(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.insertPeople(groupVO);
	}

	@Override
	public int updateGroupIn(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.updateGroupIn(groupVO);
	}
	@Override
	public int updateUser(GroupVO groupVO) {
		// TODO Auto-generated method stub
		return groupDao.updateUser(groupVO);
	}

	@Override
	public int delete(long g_seq) {
		// TODO Auto-generated method stub
		return groupDao.delete(g_seq);
	}

	@Override
	public int deletePeople(long j_seq) {
		// TODO Auto-generated method stub
		return groupDao.deletePeople(j_seq);
	}

	@Override
	public void create_group_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_join_group_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int periodCheck(String create_date,String end_date){
		// 시작일부터 종료일까지 기간 구하기
		String[] createDateStr = create_date.split("-"); 
		String[] endDateStr = end_date.split("-");
		int[] createDateLong = new int[3];
		int[] endDateLong = new int[3];
		
		for(int i=0; i < createDateStr.length; i++) {
			int createDate = Integer.valueOf(createDateStr[i]);
			createDateLong[i] = createDate; 
			int endDate = Integer.valueOf(endDateStr[i]);
			endDateLong[i] = endDate; 
		}
			
		Calendar cal1 = new GregorianCalendar(createDateLong[0],createDateLong[1]-1,createDateLong[2]);
		Calendar cal2 = new GregorianCalendar(endDateLong[0],endDateLong[1]-1,endDateLong[2]);

		long diffSec = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) /1000;
		int diffDays = (int) diffSec / (24*60*60); 
		
		return diffDays;
	}
	
	public int dDaycheck(String end_date) {
		
		Date curDate = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(curDate);
		
		String[] todayDateStr = today.split("-");
		String[] endDateStr = end_date.split("-");
		int[] createDateLong = new int[3];
		int[] endDateLong = new int[3];
		
		for(int i=0; i < todayDateStr.length; i++) {
			int createDate = Integer.valueOf(todayDateStr[i]);
			createDateLong[i] = createDate; 
			int endDate = Integer.valueOf(endDateStr[i]);
			endDateLong[i] = endDate; 
		}
			
		Calendar cal1 = new GregorianCalendar(createDateLong[0],createDateLong[1]-1,createDateLong[2]);
		Calendar cal2 = new GregorianCalendar(endDateLong[0],endDateLong[1]-1,endDateLong[2]);

		long diffSec = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) /1000;
		int diffDays = (int) diffSec / (24*60*60); 
		
		return diffDays;
		
	}
}
