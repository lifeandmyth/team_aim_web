package com.project.aim.search.dto;

public class DetailDTO {
	
	private String channel;
	private Integer subs;
	private String upload_month;
	private Integer keyword_count;
	public DetailDTO() {
		super();
	}
	public DetailDTO(String channel, Integer subs, String upload_month, Integer keyword_count) {
		super();
		this.channel = channel;
		this.subs = subs;
		this.upload_month = upload_month;
		this.keyword_count = keyword_count;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getSubs() {
		return subs;
	}
	public void setSubs(Integer subs) {
		this.subs = subs;
	}
	public String getUpload_month() {
		return upload_month;
	}
	public void setUpload_month(String upload_month) {
		this.upload_month = upload_month;
	}
	public Integer getKeyword_count() {
		return keyword_count;
	}
	public void setKeyword_count(Integer keyword_count) {
		this.keyword_count = keyword_count;
	}
	@Override
	public String toString() {
		return "DetailDTO [channel=" + channel + ", subs=" + subs + ", upload_month=" + upload_month
				+ ", keyword_count=" + keyword_count + "]";
	}
	
	
	
	


	
}