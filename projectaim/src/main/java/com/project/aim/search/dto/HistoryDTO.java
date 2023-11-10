package com.project.aim.search.dto;

public class HistoryDTO {
	
    private String keywords;
    private float keyword_count;
    private float keyword_percentage;
    
	public HistoryDTO() {
		super();
	}
	
	public HistoryDTO(String keywords, float keyword_count, float keyword_percentage) {
		super();
		this.keywords = keywords;
		this.keyword_count = keyword_count;
		this.keyword_percentage = keyword_percentage;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public float getKeyword_count() {
		return keyword_count;
	}
	
	public void setKeyword_count(float keyword_count) {
		this.keyword_count = keyword_count;
	}
	
	public float getKeyword_percentage() {
		return keyword_percentage;
	}
	
	public void setKeyword_percentage(float keyword_percentage) {
		this.keyword_percentage = keyword_percentage;
	}
	
	@Override
	public String toString() {
		return "HistoryDTO [keywords=" + keywords + ", keyword_count=" + keyword_count + ", keyword_percentage="
				+ keyword_percentage + "]";
	}
}