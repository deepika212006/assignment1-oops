package com.university.model;

public class Complaint {

    private String complaintId;
    private String description;
    private String status;

    public Complaint(String complaintId, String description) {
        this.complaintId = complaintId;
        this.description = description;
        this.status = "Pending";
    }

    public void resolve() {
        status = "Resolved";
    }
}