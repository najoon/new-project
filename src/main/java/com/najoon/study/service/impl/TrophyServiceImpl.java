package com.najoon.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.najoon.study.model.TrophyVO;
import com.najoon.study.persistance.TrophyDao;
import com.najoon.study.service.TrophyService;

@Service
public class TrophyServiceImpl implements TrophyService{
	
	@Autowired
	private TrophyDao trophyDao;

	@Override
	public List<TrophyVO> selectAll() {
		// TODO Auto-generated method stub
		return trophyDao.selectAll();
	}

	@Override
	public List<TrophyVO> findByUserTrophy(String t_username) {
		// TODO Auto-generated method stub
		return trophyDao.findByUserTrophy(t_username);
	}

	@Override
	public TrophyVO findByOneTrophy(String t_username,long t_groupseq) {
		// TODO Auto-generated method stub
		return trophyDao.findByOneTrophy(t_username, t_groupseq);
	}
	@Override
	public int insert(TrophyVO trophyVO) {
		// TODO Auto-generated method stub
		return trophyDao.insert(trophyVO);
	}

	@Override
	public int delete(long t_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTrophy(long t_seq) {
		// TODO Auto-generated method stub
		return trophyDao.updateTrophy(t_seq);
	}
}
