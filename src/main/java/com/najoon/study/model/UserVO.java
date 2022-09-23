package com.najoon.study.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserVO {
	
	public Long u_seq;
	public String u_username;
	public String u_password;
	public String u_github_id;
	public String u_email;
	
	public List<GitCommitVO> gitList;

}
