public class Doctor extends User
{
    private int id;
    private PractitionerHomePage pHomePage;

    public Doctor()
    {
        super();
        this.id=0;
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

    public PractitionerHomePage getPHomePage()
    {
        return pHomePage;
    }

}
