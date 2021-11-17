package application;

public class Patient extends User
{
    private String address;
    private String insuranceInfo;
    private String pharmacy;
    //private MedicalHistory myMedHist;
    //private MessageBoard myMessBoard;
    //private VisitSummary myVisSum;

    public Patient()
    {
        super();
        this.address="";
        this.insuranceInfo="";
        this.pharmacy="";
    }

    public Patient(String f, String l, String d, String address, String insurance, String pharm)
    {
        super(f,l,d);
        this.address=address;
        this.insuranceInfo=insurance;
        this.pharmacy=pharm;
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
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }
}