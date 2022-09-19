package com.heavenstar.zandi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitCommitVO {
	
	public Commit commit;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Commit{
		
		public Author author;
		
		@Getter
		@Setter
		@AllArgsConstructor
		@NoArgsConstructor
		@JsonIgnoreProperties(ignoreUnknown = true)
		public class Author{
			public String date;
		}	
		public String message;
	}
	
	
}

