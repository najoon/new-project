package com.najoon.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.najoon.study.model.CommentVO;
import com.najoon.study.persistance.CommentDao;
import com.najoon.study.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return commentDao.selectAll();
	}

	@Override
	public CommentVO findByComment(long c_seq) {
		// TODO Auto-generated method stub
		return commentDao.findByComment(c_seq);
	}
	
	@Override
	public List<CommentVO> findByGroupComment(long c_groupseq) {
		// TODO Auto-generated method stub
		return commentDao.findByGroupComment(c_groupseq);
	}

	@Override
	public int insert(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return commentDao.insert(commentVO);
	}

	@Override
	public int update(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long c_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
