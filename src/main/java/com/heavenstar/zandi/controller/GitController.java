package com.heavenstar.zandi.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.model.RepoListVO;
import com.heavenstar.zandi.model.UserVO;
import com.heavenstar.zandi.service.GitService;
import com.heavenstar.zandi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/git")
public class GitController {
	
	@Autowired
	private GitService gitService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String home(HttpSession session, Model model)throws IOException, ParseException{
		
		UserVO user = (UserVO)session.getAttribute("USER");
		UserVO userVO =userService.findByName(user.u_username);
		String gitName = userVO.getU_github_id();
		
		model.addAttribute("USER",userVO);
		
		List<RepoListVO> getRepoList =gitService.getRepoList(gitName);
		model.addAttribute("REPONAME",getRepoList);			
		
		String currentImg = currentImg(user.u_username);
		model.addAttribute("IMAGE",currentImg);
				
		return "git/home";
	}

	
	@RequestMapping(value="/detail_repo/{seq}",method=RequestMethod.GET)
	public String detail_repo(@PathVariable("seq") String seq, Model model,HttpSession session )throws IOException, ParseException {
		
		UserVO user = (UserVO)session.getAttribute("USER");
		UserVO userVO =userService.findByName(user.u_username);
		String gitName = userVO.getU_github_id();
		int intSeq = Integer.valueOf(seq);
		intSeq -= 1;
		
		List<RepoListVO> repoList = gitService.getRepoList(gitName);
		for(int i=0; i< repoList.size(); i++) {
			if(i == intSeq) {
				
				String repoName = repoList.get(i).name;
				model.addAttribute("REPONAME",repoName);
				List<GitCommitVO> gitList = gitService.allCommit(gitName, repoName);
				model.addAttribute("GITLIST",gitList);
				int commitOk = gitService.todayOk(gitList.get(0).commit.author.getDate());
				String check = gitCheck(commitOk);
				model.addAttribute("TODAYOK",check);
			}
		}
		return "git/detail_repo";
	}
	
	// 오늘의 커밋 검사
	public String gitCheck(int gitOk) {
		
		if(gitOk > 0) {
			return "OK";
		}else {
			return "NO";	
		}
	}
	
	//이미지 src api 불러올때 매번 다르게 로드 되게
	public String currentImg(String u_username) {
		
		Date curDate = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh-mm-ss");
		String date = dateFormat.format(curDate);
		String time = timeFormat.format(curDate);
		String img = "https://ghchart.rshah.org/"+ u_username + "?ver=" + date + "-" + time ;
		return img;
	}	
}
