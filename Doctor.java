package application;

import java.util.ArrayList;

public class Doctor extends User
{
    private int id;
    private ArrayList<Patient> patients;
    //private PractitionerHomePage pHomePage;

    public Doctor()
    {
        super();
        this.id=0;
        patients = new ArrayList<Patient>();
        
    }
    public Doctor(String f, String l, String d, int id)
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
        return pHomePage;
    }
*/
}
