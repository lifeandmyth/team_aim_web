package com.project.aim.search.dto;

/* 검색 결과를 담는 DTO */
public class SearchDTO {

    private String channel;
    private Integer subscriber_count;
    private Integer last_12_months_video_count;
    private Integer keyword_occurrence_count;
    private Integer last_12_month_video_views;
    private String channel_image;
    private long keyword_effect_score;
    
	public SearchDTO() {
		super();
	}

	public SearchDTO(String channel, Integer subscriber_count, Integer last_12_months_video_count,
			Integer keyword_occurrence_count, Integer last_12_month_video_views, String channel_image,
			long keyword_effect_score) {
		super();
		this.channel = channel;
		this.subscriber_count = subscriber_count;
		this.last_12_months_video_count = last_12_months_video_count;
		this.keyword_occurrence_count = keyword_occurrence_count;
		this.last_12_month_video_views = last_12_month_video_views;
		this.channel_image = channel_image;
		this.keyword_effect_score = keyword_effect_score;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getSubscriber_count() {
		return subscriber_count;
	}

	public void setSubscriber_count(Integer subscriber_count) {
		this.subscriber_count = subscriber_count;
	}

	public Integer getLast_12_months_video_count() {
		return last_12_months_video_count;
	}

	public void setLast_12_months_video_count(Integer last_12_months_video_count) {
		this.last_12_months_video_count = last_12_months_video_count;
	}

	public Integer getKeyword_occurrence_count() {
		return keyword_occurrence_count;
	}

	public void setKeyword_occurrence_count(Integer keyword_occurrence_count) {
		this.keyword_occurrence_count = keyword_occurrence_count;
	}

	public Integer getLast_12_month_video_views() {
		return last_12_month_video_views;
	}

	public void setLast_12_month_video_views(Integer last_12_month_video_views) {
		this.last_12_month_video_views = last_12_month_video_views;
	}

	public String getChannel_image() {
		return channel_image;
	}

	public void setChannel_image(String channel_image) {
		this.channel_image = channel_image;
	}

	public long getKeyword_effect_score() {
		return keyword_effect_score;
	}

	public void setKeyword_effect_score(long keyword_effect_score) {
		this.keyword_effect_score = keyword_effect_score;
	}

	@Override
	public String toString() {
		return "SearchDTO [channel=" + channel + ", subscriber_count=" + subscriber_count
				+ ", last_12_months_video_count=" + last_12_months_video_count + ", keyword_occurrence_count="
				+ keyword_occurrence_count + ", last_12_month_video_views=" + last_12_month_video_views
				+ ", channel_image=" + channel_image + ", keyword_effect_score=" + keyword_effect_score + "]";
	}

	
	
}
	