package com.najoon.study.controller;

import java.text.ParseException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.najoon.study.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) throws ParseException {
		
		UserVO userVO = (UserVO)session.getAttribute("USER");
		
		if(userVO == null) {
			model.addAttribute("LAYOUT","NOT_LOGIN");
		}
		return "home";
	}
}
