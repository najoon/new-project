package com.najoon.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.najoon.study.model.UserVO;
import com.najoon.study.service.GitService;
import com.najoon.study.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		
		
		return null;
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(UserVO userVO, Model model) {
		
		log.debug("확인1:{}",userVO.getU_username());
		log.debug("확인2:{}",userVO.u_username);
		UserVO joinVO = userService.findByName(userVO.getU_username());
		log.debug("확인3:{}",joinVO);
		
		
		if(joinVO == null) {
			userService.insert(userVO);
			
			return "redirect:/user/login";
		}
		
		return "user/join";		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		
		return "user/login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(UserVO userVO,Model model, HttpSession session) {
		
		
		
		UserVO user = userService.findByName(userVO.u_username);
		
		log.debug("로그인:{}",userVO);
		if(user == null) {
			log.debug("아이디가 틀렸음:{}",user);
			model.addAttribute("error","LOGIN_FAIL");
			return "user/login";
		}
		
		if(user.u_password.equals(userVO.u_password)) {
			log.debug("비밀번호 맞음",user);
			session.setAttribute("USER", userVO);
			return "redirect:/";
		}
		
		log.debug("비밀번호 틀림",user);
		model.addAttribute("error","LOGIN_FAIL");
		return "user/login";
	}

	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.setAttribute("USER", null);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/joinout",method=RequestMethod.GET)
	public String joinout() {
		
		return "user/joinout";
	}
}
