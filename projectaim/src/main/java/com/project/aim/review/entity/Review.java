package com.project.aim.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="review_info")
public class Review {

	@Id
    @Column(name = "review_idx")
    private int channel_idx;
   
    @Column(name = "manager_id")
    private int manager_id;
   
    @Column(name = "title")
    private String title;
    
    @Column(name = "views")
    private int views;
    
    @Column(name = "upload_date")
    private String upload_date;
    
    @Column(name = "cmt_vibe_good")
    private int cmt_vibe_good;
    
    @Column(name = "cmt_vibe_bad")
    private int cmt_vibe_bad;
    
    @Column(name = "thumb_url")
    private String thumb_url;
    
    @Column(name = "vid_url")
    private String vid_url;

	public Review() {
		super();
	}

	public Review(int channel_idx, int manager_id, String title, int views, String upload_date, int cmt_vibe_good,
			int cmt_vibe_bad, String thumb_url, String vid_url) {
		super();
		this.channel_idx = channel_idx;
		this.manager_id = manager_id;
		this.title = title;
		this.views = views;
		this.upload_date = upload_date;
		this.cmt_vibe_good = cmt_vibe_good;
		this.cmt_vibe_bad = cmt_vibe_bad;
		this.thumb_url = thumb_url;
		this.vid_url = vid_url;
	}

	public int getChannel_idx() {
		return channel_idx;
	}

	public void setChannel_idx(int channel_idx) {
		this.channel_idx = channel_idx;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public int getCmt_vibe_good() {
		return cmt_vibe_good;
	}

	public void setCmt_vibe_good(int cmt_vibe_good) {
		this.cmt_vibe_good = cmt_vibe_good;
	}

	public int getCmt_vibe_bad() {
		return cmt_vibe_bad;
	}

	public void setCmt_vibe_bad(int cmt_vibe_bad) {
		this.cmt_vibe_bad = cmt_vibe_bad;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public String getVid_url() {
		return vid_url;
	}

	public void setVid_url(String vid_url) {
		this.vid_url = vid_url;
	}

	@Override
	public String toString() {
		return "Review [channel_idx=" + channel_idx + ", manager_id=" + manager_id + ", title=" + title + ", views="
				+ views + ", upload_date=" + upload_date + ", cmt_vibe_good=" + cmt_vibe_good + ", cmt_vibe_bad="
				+ cmt_vibe_bad + ", thumb_url=" + thumb_url + ", vid_url=" + vid_url + "]";
	}
}
