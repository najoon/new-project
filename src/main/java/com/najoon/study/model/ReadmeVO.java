package com.najoon.study.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadmeVO {
	
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("documentation_url")
	private String documentation_url;
	
	
	@JsonProperty("name")
	private String name;
	@JsonProperty("path")
	private String path;
	@JsonProperty("sha")
	private String sha;
	@JsonProperty("size")
	private String size;
	@JsonProperty("url")
	private String url;
	@JsonProperty("html_url")
	private String html_url;
	@JsonProperty("git_url")
	private String git_url;
	@JsonProperty("download_url")
	private String download_url;
	@JsonProperty("type")
	private String type;
	@JsonProperty("content")
	private String content;
	@JsonProperty("encoding")
	private String encoding;
	
	@JsonProperty("_links")
	public _Links _links;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	class _Links{
		@JsonProperty("self")
		private String self;
		@JsonProperty("git")
		private String git;
		@JsonProperty("html")
		private String html;
	}
}
