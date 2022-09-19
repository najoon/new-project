package com.heavenstar.zandi.persistance;

import java.util.List;

import com.heavenstar.zandi.model.CommentVO;

public interface CommentDao {
	
	public List<CommentVO> selectAll();
	public CommentVO findByComment(long c_seq);
	public List<CommentVO> findByGroupComment(long c_groupseq);
	public int insert(CommentVO commentVO);
	public int update(CommentVO commentVO);
	public int delete(long c_seq);
	
}
