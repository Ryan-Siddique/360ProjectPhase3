package com.example.demo;


public class MedicalHistory {
    private String previousIssues;
    private String previousPrescriptions;
    private String immRecord;

    public MedicalHistory(){
        this.previousIssues = "";
        this.previousPrescriptions = "";
        this.immRecord = "";
    }

    public MedicalHistory(String i, String p, String r){
        this.previousIssues = i;
        this.previousPrescriptions = p;
        this.immRecord = r;
    }
    public String getPreviousIssues() {
        return previousIssues;
    }

    public void setPreviousIssues(String previousIssues) {
        this.previousIssues = previousIssues;
    }

    public String getPreviousPrescriptions() {
        return previousPrescriptions;
    }

    public void setPreviousPrescriptions(String previousPrescriptions) {
        this.previousPrescriptions = previousPrescriptions;
    }

    public String getImmRecord() {
        return immRecord;
    }

    public void setImmRecord(String immRecord) {
        this.immRecord = immRecord;
    }

}
