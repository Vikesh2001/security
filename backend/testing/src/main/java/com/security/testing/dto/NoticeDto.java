package com.security.testing.dto;

import java.time.LocalDate;

import com.security.testing.entity.NoticePriority;

public class NoticeDto {
    private Long id;
    private String title;
    private String content;
    private LocalDate date;
    private Long createdById;
    private String createdByName;
    private NoticePriority priority;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long getCreatedById() {
		return createdById;
	}
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public NoticePriority getPriority() {
		return priority;
	}
	public void setPriority(NoticePriority priority) {
		this.priority = priority;
	}
    
    
}
