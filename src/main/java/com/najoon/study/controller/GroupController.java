package com.najoon.study.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.najoon.study.model.CommentVO;
import com.najoon.study.model.GroupVO;
import com.najoon.study.model.RepoListVO;
import com.najoon.study.model.ToOkVO;
import com.najoon.study.model.TrophyVO;
import com.najoon.study.model.UserVO;
import com.najoon.study.service.CommentService;
import com.najoon.study.service.GitService;
import com.najoon.study.service.GroupService;
import com.najoon.study.service.TrophyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GitService gitService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private TrophyService trophyService;
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String group(String seq,HttpSession session, Model model) {
		
		String userName = username(session);
		model.addAttribute("USER",userName);
		
		if(seq != null) {
			long longSeq = Long.valueOf(seq);	
			GroupVO groupName = groupService.findByGroup(longSeq);
			model.addAttribute("FULLIN",groupName.getG_name());	
		}
		
		List<TrophyVO> trophyList = trophyService.findByUserTrophy(userName);
		for(int i =0; i < trophyList.size(); i++) {
			if(trophyList.get(i).isT_trophy() == false) {
				trophyList.remove(i);
			}
		}
		model.addAttribute("TROPHY",trophyList);
		
		List<GroupVO> groupList = groupService.selectAll();
		for(int i=0; i < groupList.size(); i++) {
			String cDate = groupList.get(i).getG_create_date();
			String eDate = groupList.get(i).getG_end_date();
			int period = groupService.periodCheck(cDate, eDate);
			if(period != 0) {
				String strPeriod = String.valueOf(period);
				groupList.get(i).setPeriod(strPeriod);
			}			
		}
		model.addAttribute("GROUPLIST", groupList);
		
		return "group/home";
	}
	@RequestMapping(value={"/",""},method=RequestMethod.POST)
	public String group(GroupVO groupVO, HttpSession session) {
		
		Date currDate = new Date(System.currentTimeMillis());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(currDate);
		
		Date dateDate = groupVO.getEnd_date();
		String endDay = dateFormat.format(dateDate);
		
		groupVO.setG_inpeople(0);
		groupVO.setG_create_date(today);
		groupVO.setG_end_date(endDay);
		groupService.insert(groupVO);	
		
		List<GroupVO> groupList =groupService.selectAll();
		int size = groupList.size() -1;
		long longSeq = groupList.get(size).getG_seq();
		
		//인원 처리
		GroupVO groupName = groupService.findByGroup(longSeq);
		int count = groupName.getG_inpeople();
		count += 1;
		groupName.setG_inpeople(count);
		groupService.updateGroupIn(groupName);
		
		GroupVO group = new GroupVO();
		group.setJ_gname(groupName.getG_name());
		group.setJ_username(username(session));
		group.setJ_percent("0.00");
		groupService.insertPeople(group);
		
		return "redirect:/group/group_in/" + longSeq;
	}

	@RequestMapping(value="/group_in/{g_seq}",method=RequestMethod.GET)
	public String group_in(@PathVariable("g_seq") String g_seq,HttpSession session,
			Model model) throws IOException, ParseException, java.text.ParseException {
		
		String userName = username(session);
		long longSeq = Long.valueOf(g_seq);
		GroupVO groupName = groupService.findByGroup(longSeq);
		model.addAttribute("GROUP",groupName);	
		GroupVO group = new GroupVO();
		group.setJ_gname(groupName.getG_name());
		group.setJ_username(userName);

		List<GroupVO> peopleCheckList = groupService.findByGroupPeople(groupName.getG_name());
		
		//출석 완료율 구하기
		int period = groupService.periodCheck(groupName.getG_create_date(),groupName.getG_end_date()) +1;
		for(int i=0; i < peopleCheckList.size();i++) {
			GroupVO userGroupVO = groupService.findByOnePeople(groupName.getG_name(), peopleCheckList.get(i).getJ_username());
			double douPercent = 100 * (userGroupVO.getJ_count() / (double)period);
			String strPercent = String.format("%.2f", douPercent);
			peopleCheckList.get(i).setJ_percent(strPercent);
		}
	
		//스터디 종료일까지 남은기간
		int dDayCheck = groupService.dDaycheck(groupName.getG_end_date());
		if(dDayCheck == 0) {
			//기간이 종료되면 D-DAY 보내기
			model.addAttribute("DDAY", "DAY");			
		}else {			
			model.addAttribute("DDAY",dDayCheck);
		}
	
		//trophy 값 구하기
		TrophyVO trophyVO = trophyService.findByOneTrophy(userName, longSeq);
		//트로피가 없으면 실행
		if(trophyVO == null) {
			GroupVO userGroupVO = groupService.findByOnePeople(groupName.getG_name(),userName);
			int userCount = 0;
			if(userGroupVO  != null) {
				userCount = userGroupVO.getJ_count();
			}
			double douPercent = 100 * ( userCount/ (double)period);
			String strPercent = String.format("%.2f", douPercent);
			// 완료율을 계산해서 80%가 넘었으면 트로피 생성
			if(percentCheck(douPercent) == true) {
				TrophyVO newTrophy = new TrophyVO();
				newTrophy.setT_username(userName);
				newTrophy.setT_groupseq(longSeq);
				newTrophy.setT_groupname(groupName.getG_name());
				newTrophy.setT_trophy(percentCheck(douPercent));
				newTrophy.setT_complete(strPercent);
				trophyService.insert(newTrophy);				
			}
		}

		List<CommentVO> commentList = commentService.findByGroupComment(longSeq);
		model.addAttribute("COMMENT",commentList);
		
		//입장 처리
		int count = groupName.getG_inpeople();
		for(int i =0; i < peopleCheckList.size(); i++) {	
			// 이미 가입자면 바로 보내기
			if(peopleCheckList.get(i).getJ_username().equals(userName)) {
				model.addAttribute("PEOPLELIST",peopleCheckList);
				return "group/group_in";
			}			
		}
		//인원 다 차면 들어오지 못하게
		if(peopleCheckList.size() >= groupName.getG_people()) {
			model.addAttribute("seq",longSeq);
			return "redirect:/group";
		}
		
		//새로 가입하면 insert
		count += 1;
		groupName.setG_inpeople(count);
		groupService.updateGroupIn(groupName);
		group.setJ_percent("0.00");
		groupService.insertPeople(group);
		
		List<GroupVO> insertPeopleList = groupService.findByGroupPeople(groupName.getG_name());
		model.addAttribute("PEOPLELIST",insertPeopleList);
		
		return "group/group_in";
		
	}
	
	@RequestMapping(value="/group_in/{g_seq}",method=RequestMethod.POST)
	public String comment(@PathVariable("g_seq")String g_seq, CommentVO commentVO, HttpSession session,
			HttpServletResponse response, HttpServletRequest request ) throws java.text.ParseException {
		
		String userName = username(session);
		long c_groupseq = Long.valueOf(g_seq);
		
		//CommentVO에 날짜 담아서 기록하기
		Date current = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String date = dateFormat.format(current);
		String time = timeFormat.format(current);
		
		commentVO.setC_username(userName);
		commentVO.setC_groupseq(c_groupseq);
		commentVO.setC_date(date);
		commentVO.setC_time(time);
		
		commentService.insert(commentVO);
		
		GroupVO group = groupService.findByGroup(c_groupseq);
		GroupVO user =  groupService.findByOnePeople(group.getG_name(), userName);
		int commentCount = user.getJ_count();
		
		//이미 쿠키에 저장되었는지 확인하기
		Cookie[] cookies = request.getCookies();
		boolean check = false;
		for(int i=0; i < cookies.length; i++) {
			//일치하하는 name 값 가져오기
			if(cookies[i].getName().equals(userName + c_groupseq)){
				// name값의 value가져와서 오늘 날짜와 비교
				if(!cookies[i].getValue().equals(date)) {					
					//일치 안하면 저장된 쿠키 삭제후 카운트 1 올리고, 오늘 날짜의 쿠키 생성
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
					commentCount +=1;
					user.setJ_count(commentCount);
					groupService.updateUser(user);
					Cookie cookie = new Cookie(userName + c_groupseq,date);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
				}
				check = true;
			}
		}
		//일치 값이 없을 경우 카운트 올리고 쿠키 값 저장
		if(check == false) {
			commentCount +=1;
			user.setJ_count(commentCount);
			groupService.updateUser(user);
			Cookie cookie = new Cookie(userName + c_groupseq,date);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24);
			response.addCookie(cookie);
		}
		
		return "redirect:/group/group_in/" +g_seq;
	}
	
	@RequestMapping(value="/out/{g_seq}",method=RequestMethod.GET)
	public String groupOut(@PathVariable("g_seq") String g_seq, HttpSession session) {
		
		UserVO user = (UserVO)session.getAttribute("USER");
		String userName = user.u_username;
		long longSeq = Long.valueOf(g_seq);
		
		GroupVO groupVO = groupService.findByGroup(longSeq);
		String groupName = groupVO.getG_name();
		GroupVO people = groupService.findByOnePeople(groupName, userName);
		groupService.deletePeople(people.getJ_seq());	
		
		//가입 인원이 들어오면 g_inpeople에 1씩 감소하여
		//인원수 카운트 줄이기
		int count = groupVO.getG_inpeople();
		count -= 1;
		groupVO.setG_inpeople(count);
		groupService.updateGroupIn(groupVO);
		
		// 인원이 0이 되면 그룹 삭제
		if(count < 1) {
			groupService.delete(longSeq);
		}
		
		return "redirect:/group";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list_go() {
		
		return "redirect:/group";
	}
	
	public String username(HttpSession session) {
		
		UserVO user = (UserVO)session.getAttribute("USER");
		String userName = user.u_username;
		return userName;
	}
	
	//오늘 커밋 완료 검사
	public List<ToOkVO> todayCommitCheck(List<GroupVO> peopleList) throws IOException, ParseException{
		
				List<ToOkVO> okList = new ArrayList<>();
				int gitOk = 0;
				for(int i =0; i < peopleList.size(); i++) {
					ToOkVO toOK = new ToOkVO();
					String username = peopleList.get(i).getJ_username();
					toOK.setUsername(username);
					List<RepoListVO> repoList = gitService.getRepoList(username);
					
					for(int j =0; j< repoList.size(); j++) {
						String repoName = repoList.get(j).getName();
						gitOk += gitService.CommitOk(username, repoName);
					}	
					if(gitOk > 0) {
						toOK.setMessage("완료");
					}else {
						toOK.setMessage("미완료");
					}
					okList.add(toOK);
				}
		return okList;
	}
	
	//TODO 퍼센트 체크(완료율)
	public boolean percentCheck(Double douPercent) {
		if(douPercent >= 10) {
			return true;
		}else {
			return false;
		}
	}
}
