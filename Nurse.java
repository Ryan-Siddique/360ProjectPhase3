package application;

import java.util.ArrayList;

public class Nurse extends User
{
    private int id;
    private ArrayList<Patient> patients;
   // private PractitionerHomePage pHomePage;

    public Nurse()
    {
        super();
        this.id=0;
        patients = new ArrayList<Patient>();
        //this pHomePage=null;
    }
    public Nurse(String f, String l, String d, int id)
    {
        super(f,l,d);
        this.id=id;
    }

    public void setID(int id)
    {
        this.id=id;
    }
    public ArrayList<Patient> getPatientList() {
        return patients;
    }

/*    public PractitionerHomePage getPHomePage()
    {
        return this.pHomePage;
    }
 */
}
