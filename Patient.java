package application;

public class Patient extends User
{
    private int id;
    private String address;
    private String insuranceInfo;
    private String pharmacy;
    //private MedicalHistory myMedHist;
    //private MessageBoard myMessBoard;
    private VisitSummary myVisSum;

    public Patient()
    {
        super();
        
        id = 0;
        this.address="";
        this.insuranceInfo="";
        this.pharmacy="";
        myVisSum = new VisitSummary();
    }

    public Patient(String f, String l, String d, String address, String insurance, String pharm)
    {
        super(f,l,d);
        this.address=address;
        this.insuranceInfo=insurance;
        this.pharmacy=pharm;
    }
    public void setID(int id) {
        this.id = id;
    }

    public void editAddress(String address)
    {
        this.address=address;
    }
    public void editInsuranceInfo(int id, int groupid, String name)
    {
        String ans="ID: " + id + "\nGroupID: " + groupid + "\nName: " + name;
        this.insuranceInfo=ans;
    }
    public void editPharmacy(String address,String name)
    {
        String ans="Name: " + name + "\nAddress: " + address;
    }
   
    public void setVisitSummary(int weight, int height, int temp, String pressure, String allergies, String concerns, String diagnosis, String notes, String presName, String presAmt) 
    {
        myVisSum.setWeight(weight);
        myVisSum.setHeight(height);
        myVisSum.setBodyTemperature(temp);
        myVisSum.setBloodPressure(pressure);
        myVisSum.setAllergies(allergies);
        myVisSum.setPatientConcerns(concerns);
        myVisSum.setDiagnosis(diagnosis);
        myVisSum.setNotes(notes);
        myVisSum.setMedicationName(presName);
        myVisSum.setMedicationAmount(presAmt);
    }
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }
    
}