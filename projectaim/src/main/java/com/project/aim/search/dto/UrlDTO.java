package com.project.aim.search.dto;

public class UrlDTO {
   private String channel;
   private String title;
   private String upload_date;
   private String vid_url;
   private int timeline;
   private int keyword_occurrence;
   
   public UrlDTO() {
      super();
   }

   public String getChannel() {
      return channel;
   }

   public void setChannel(String channel) {
      this.channel = channel;
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

   public String getVid_url() {
      return vid_url;
   }

   public void setVid_url(String vid_url) {
      this.vid_url = vid_url;
   }

   public int getTimeline() {
      return timeline;
   }

   public void setTimeline(int timeline) {
      this.timeline = timeline;
   }

   public int getKeyword_occurrence() {
      return keyword_occurrence;
   }

   public void setKeyword_occurrence(int keyword_occurrence) {
      this.keyword_occurrence = keyword_occurrence;
   }

   @Override
   public String toString() {
      return "UrlDTO [channel=" + channel + ", title=" + title + ", upload_date=" + upload_date + ", vid_url="
            + vid_url + ", timeline=" + timeline + ", keyword_occurrence=" + keyword_occurrence + "]";
   }

   public UrlDTO(String channel, String title, String upload_date, String vid_url, int timeline,
         int keyword_occurrence) {
      super();
      this.channel = channel;
      this.title = title;
      this.upload_date = upload_date;
      this.vid_url = vid_url;
      this.timeline = timeline;
      this.keyword_occurrence = keyword_occurrence;
   }

   
   
   

}