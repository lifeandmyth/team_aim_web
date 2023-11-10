package com.project.aim.main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.aim.search.entity.Channel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "video_info")

public class Video {
	
	@Id
	@Column(name = "idx")
	private int idx;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "channel_idx", referencedColumnName = "channel_idx")
	private Channel channel;
	
	@Column(name = "views")
	private int views;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "upload_date")
	private String upload_date;
	
	@Column(name = "keywords")
	private String keywords;
	
	@Column(name = "timeline")
	private int timeline;
	
	@Column(name = "likes")
	private int likes;
	
	@Column(name = "vid_url")
	private String vid_url;

	public Video() {
		super();
	}

	public Video(int idx, Channel channel, int views, String title, String upload_date, String keywords, int timeline,
			int likes, String vid_url) {
		super();
		this.idx = idx;
		this.channel = channel;
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

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
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
		return "Video [idx=" + idx + ", channel=" + channel + ", views=" + views + ", title=" + title + ", upload_date="
				+ upload_date + ", keywords=" + keywords + ", timeline=" + timeline + ", likes=" + likes + ", vid_url="
				+ vid_url + "]";
	}

	
	
}









