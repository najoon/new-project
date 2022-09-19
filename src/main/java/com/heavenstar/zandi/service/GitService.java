package com.heavenstar.zandi.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.model.ReadmeVO;
import com.heavenstar.zandi.model.RepoListVO;

public interface GitService {
	
	//레포 리트스 가져오기
	public List<RepoListVO> getRepoList(String username)throws IOException, ParseException;
	
	//커밋 가져와서 오늘 커밋 확인
	public int CommitOk(String id, String repo) throws IOException, ParseException;
	
	// 리드미 가져올때
	public ReadmeVO getReadme(String id, String repo) throws IOException, ParseException;
	
	// 모든 깃 가져올때
	public List<GitCommitVO> allCommit(String id, String repo) throws IOException, ParseException;
	
	
	// 커밋 날짜 변환
	public String dataTransate(String date);
	
	//오늘 커밋 확인
	public int todayOk(String date);
	
	//리드미 변환
	public String readmeTransate(String readme)throws UnsupportedEncodingException;
	
	
}
