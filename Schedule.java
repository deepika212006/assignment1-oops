package com.university.model;

public class Schedule {

    private String day;
    private String startTime;
    private String endTime;
    private String room;

    public Schedule(String day, String startTime, String endTime, String room) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }
}