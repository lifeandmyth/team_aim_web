package com.project.aim.main.dto;


/* 메인 페이지에서 사용하는 DTO */
public class ChannelVideoDTO {

   private String channel;
   private Integer subscriber_count;
   private String keyword;
   private Integer keyword_count;
   private String channel_image;
   
   public ChannelVideoDTO() {
	   super();
   }

	public ChannelVideoDTO(String channel, Integer subscriber_count, String keyword, Integer keyword_count,
			String channel_image) {
		super();
		this.channel = channel;
		this.subscriber_count = subscriber_count;
		this.keyword = keyword;
		this.keyword_count = keyword_count;
		this.channel_image = channel_image;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getKeyword_count() {
		return keyword_count;
	}

	public void setKeyword_count(Integer keyword_count) {
		this.keyword_count = keyword_count;
	}

	public String getChannel_image() {
		return channel_image;
	}

	public void setChannel_image(String channel_image) {
		this.channel_image = channel_image;
	}

	@Override
	public String toString() {
		return "ChannelVideoDTO [channel=" + channel + ", subscriber_count=" + subscriber_count + ", keyword=" + keyword
				+ ", keyword_count=" + keyword_count + ", channel_image=" + channel_image + "]";
	}

   
}
   