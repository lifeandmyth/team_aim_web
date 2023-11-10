package com.project.aim.search.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "keyword_history")
public class KeywordHistory {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_idx")
    private long history_idx;
   
    @Column(name = "keywords")
    private String keywords;
   
    @Column(name = "search_date")
    private LocalDateTime searchDate;

	public KeywordHistory() {
		super();
	}

	public KeywordHistory(long history_idx, String keywords, LocalDateTime searchDate) {
		super();
		this.history_idx = history_idx;
		this.keywords = keywords;
		this.searchDate = searchDate;
	}

	public long getHistory_idx() {
		return history_idx;
	}

	public void setHistory_idx(long history_idx) {
		this.history_idx = history_idx;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public LocalDateTime getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(LocalDateTime searchDate) {
		this.searchDate = searchDate;
	}

	@Override
	public String toString() {
		return "KeywordHistory [history_idx=" + history_idx + ", keywords=" + keywords + ", searchDate=" + searchDate
				+ "]";
	}

	

}








