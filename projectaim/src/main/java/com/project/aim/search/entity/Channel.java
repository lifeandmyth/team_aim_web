package com.project.aim.search.entity;

import java.util.List;

import com.project.aim.main.entity.Video;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "channel_info")
public class Channel {
   
   @Id
   @Column(name = "channel_idx")
   private int channel_idx;
   
   @Column(name = "channel")
   private String channel;
   
   @Column(name = "channel_image")
   private String channel_image;
   
   @Column(name = "subs")
   private int subs;
   
   @OneToMany(mappedBy = "channel")
    private List<Video> videos;
   
   public Channel() {
      super();
   }

   public Channel(int channel_idx, String channel, String channel_image, int subs, List<Video> videos) {
      super();
      this.channel_idx = channel_idx;
      this.channel = channel;
      this.channel_image = channel_image;
      this.subs = subs;
      this.videos = videos;
   }

   public int getChannel_idx() {
      return channel_idx;
   }

   public void setChannel_idx(int channel_idx) {
      this.channel_idx = channel_idx;
   }

   public String getChannel() {
      return channel;
   }

   public void setChannel(String channel) {
      this.channel = channel;
   }

   public String getChannel_image() {
      return channel_image;
   }

   public void setChannel_image(String channel_image) {
      this.channel_image = channel_image;
   }

   public int getSubs() {
      return subs;
   }

   public void setSubs(int subs) {
      this.subs = subs;
   }

   public List<Video> getVideos() {
      return videos;
   }

   public void setVideos(List<Video> videos) {
      this.videos = videos;
   }

   @Override
   public String toString() {
      return "Channel [channel_idx=" + channel_idx + ", channel=" + channel + ", channel_image=" + channel_image
            + ", subs=" + subs + ", videos=" + videos + "]";
   }


   
}








