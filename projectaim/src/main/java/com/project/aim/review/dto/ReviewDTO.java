package com.project.aim.review.dto;


public class ReviewDTO {

	private int channelIdx;
    private int managerId;
    private String title;
    private int views;
    private String uploadDate;
    private int cmtVibeGood;
    private int cmtVibeBad;
    private String thumbUrl;
    private String vidUrl;

	public ReviewDTO() {
		super();
	}

	public ReviewDTO(int channelIdx, int managerId, String title, int views, String uploadDate, int cmtVibeGood,
			int cmtVibeBad, String thumbUrl, String vidUrl) {
		super();
		this.channelIdx = channelIdx;
		this.managerId = managerId;
		this.title = title;
		this.views = views;
		this.uploadDate = uploadDate;
		this.cmtVibeGood = cmtVibeGood;
		this.cmtVibeBad = cmtVibeBad;
		this.thumbUrl = thumbUrl;
		this.vidUrl = vidUrl;
	}

	public int getchannelIdx() {
		return channelIdx;
	}

	public void setchannelIdx(int channelIdx) {
		this.channelIdx = channelIdx;
	}

	public int getmanagerId() {
		return managerId;
	}

	public void setmanagerId(int managerId) {
		this.managerId = managerId;
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

	public String getuploadDate() {
		return uploadDate;
	}

	public void setuploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getcmtVibeGood() {
		return cmtVibeGood;
	}

	public void setcmtVibeGood(int cmtVibeGood) {
		this.cmtVibeGood = cmtVibeGood;
	}

	public int getcmtVibeBad() {
		return cmtVibeBad;
	}

	public void setcmtVibeBad(int cmtVibeBad) {
		this.cmtVibeBad = cmtVibeBad;
	}

	public String getthumbUrl() {
		return thumbUrl;
	}

	public void setthumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getvidUrl() {
		return vidUrl;
	}

	public void setvidUrl(String vidUrl) {
		this.vidUrl = vidUrl;
	}

	@Override
	public String toString() {
		return "ReviewDTO [channelIdx=" + channelIdx + ", managerId=" + managerId + ", title=" + title + ", views="
				+ views + ", uploadDate=" + uploadDate + ", cmtVibeGood=" + cmtVibeGood + ", cmtVibeBad="
				+ cmtVibeBad + ", thumbUrl=" + thumbUrl + ", vidUrl=" + vidUrl + "]";
	}
}