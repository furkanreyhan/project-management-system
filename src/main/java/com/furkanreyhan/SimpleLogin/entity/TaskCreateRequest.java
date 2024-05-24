package com.furkanreyhan.SimpleLogin.entity;

public class TaskCreateRequest {
    String title;
    String description;
    //Taskın atanacağı userId
    Long userId;

    public TaskCreateRequest() {
    }

    public TaskCreateRequest(String title, String description, Long userId) {

        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TaskCreateRequest{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
