package com.project.aim.main.dto;

public class VideoDTO {
	
	private int idx;
	private int channel_idx;
	private int views;
	private String title;
	private String upload_date;
	private String keywords;
	private int timeline;
	private int likes;
	private String vid_url;
	
	public VideoDTO() {
		super();
	}

	public VideoDTO(int idx, int channel_idx, int views, String title, String upload_date, String keywords,
			int timeline, int likes, String vid_url) {
		super();
		this.idx = idx;
		this.channel_idx = channel_idx;
		this.views = views;
		this.title = title;
		this.upload_date = upload_date;
		this.keywords = keywords;
		this.timeline = timeline;
		this.likes = likes;
		this.vid_url = vid_url;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getChannel_idx() {
		return channel_idx;
	}

	public void setChannel_idx(int channel_idx) {
		this.channel_idx = channel_idx;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getTimeline() {
		return timeline;
	}

	public void setTimeline(int timeline) {
		this.timeline = timeline;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getVid_url() {
		return vid_url;
	}

	public void setVid_url(String vid_url) {
		this.vid_url = vid_url;
	}

	@Override
	public String toString() {
		return "VideoDTO [idx=" + idx + ", channel_idx=" + channel_idx + ", views=" + views + ", title=" + title
				+ ", upload_date=" + upload_date + ", keywords=" + keywords + ", timeline=" + timeline + ", likes="
				+ likes + ", vid_url=" + vid_url + "]";
	}
}