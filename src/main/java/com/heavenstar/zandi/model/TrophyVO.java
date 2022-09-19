package com.heavenstar.zandi.model;

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
@ToString
@Builder
public class TrophyVO {
	
	private long t_seq;
	private long t_groupseq;	
	private String t_groupname;
	private String t_username;
	private String t_complete;
	private boolean t_trophy;

}
