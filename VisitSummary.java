package com.example.demo;

public class VisitSummary{
    private int weight;
    private int height;
    private int bodyTemperature;
    private int bloodPressure;
    private String allergies;
    private String patientConcerns;
    private String diagnosis;
    private String Notes;
    private String medicationName;
    private int medicationAmount;

    public VisitSummary(){
        this.weight = 0;
        this.height = 0;
        this.bodyTemperature = 0;
        this.bloodPressure = 0;
        this.allergies = "";
        this.patientConcerns = "";
        this.diagnosis = "";
        this.Notes = "";
        this.medicationName = "";
        this.medicationAmount = 0;
    }

    public VisitSummary(int w, int h, int temp, int bp, String a, String concerns, String d, String n, String medName, int medAmt){
        this.weight = w;
        this.height = h;
        this.bodyTemperature = temp;
        this.bloodPressure = bp;
        this.allergies = a;
        this.patientConcerns = concerns;
        this.diagnosis = d;
        this.Notes = n;
        this.medicationName = medName;
        this.medicationAmount = medAmt;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(int bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getPatientConcerns() {
        return patientConcerns;
    }

    public void setPatientConcerns(String patientConcerns) {
        this.patientConcerns = patientConcerns;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getMedicationAmount() {
        return medicationAmount;
    }

    public void setMedicationAmount(int medicationAmount) {
        this.medicationAmount = medicationAmount;
    }

}
