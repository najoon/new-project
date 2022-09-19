package com.heavenstar.zandi.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
public class GroupVO {
	
	//그룹VO
	public long g_seq;
	public String g_name;
	public int g_people;
	public int g_inpeople;
	public String g_create_date;
	public String g_end_date;
	
	//controller에서 기간을 구해서 model에 보내는 용도
	public String period;
	
	//form에서 지정한 end_date 받아오는것
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date end_date;
	
	//그룹 참가인원VO
	private long j_seq;
	private String j_gname;
	private String j_username;
	private int j_count;
	private String j_percent;

}
