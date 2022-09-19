package com.heavenstar.zandi.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heavenstar.zandi.model.TrophyVO;

public interface TrophyDao {
	
	public List<TrophyVO> selectAll();
	public List<TrophyVO> findByUserTrophy(String t_username);
	public TrophyVO findByOneTrophy(@Param("t_username") String t_username,@Param("t_groupseq")long t_groupseq);
	public int insert(TrophyVO trophyVO);
	public int updateTrophy(long t_seq);
	public int delete(long t_seq);

}
