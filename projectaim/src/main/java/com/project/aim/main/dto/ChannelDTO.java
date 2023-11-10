package com.project.aim.main.dto;

public class ChannelDTO {
	
	private int channel_idx;
	private String channel;
	private int subs;
	
	public ChannelDTO() {
		super();
	}

	public ChannelDTO(int channel_idx, String channel, int subs) {
		super();
		this.channel_idx = channel_idx;
		this.channel = channel;
		this.subs = subs;
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

	public int getSubs() {
		return subs;
	}

	public void setSubs(int subs) {
		this.subs = subs;
	}

	@Override
	public String toString() {
		return "ChannelDTO [channel_idx=" + channel_idx + ", channel=" + channel + ", subs=" + subs + "]";
	}
}