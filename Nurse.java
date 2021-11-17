package application;

public class Nurse extends User
{
    private int id;
   // private PractitionerHomePage pHomePage;

    public Nurse()
    {
        super();
        this.id=0;
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

/*    public PractitionerHomePage getPHomePage()
    {
        return this.pHomePage;
    }
 */
}
