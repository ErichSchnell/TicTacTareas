package com.practicas.TicTacTareas.entity;

public class Task {
    private int id;
    private String title;
    private String description;
    private String state;
    private Long timeout;

    public Task() {
    }

    public Task(int id, String title, String description, String state, Long timeout) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.timeout = timeout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}