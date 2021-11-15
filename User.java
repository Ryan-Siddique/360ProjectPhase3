public class User {
    private String fname;
    private String lname;
    private String dob;

    public User() {
        this.fname = "";
        this.lname = "";
        this.dob = "";
    }

    public User(String f, String l, String d) {
        this.fname = f;
        this.lname = l;
        this.dob = d;
    }

    public String getFirstName()
    {
        return fname;
    }
    public String getLastName()
    {
        return lname;
    }
    public void setFirstName(String f)
    {
        this.fname=f;
    }
    public void setLastName(String l)
    {
        this.lname=l;
    }
    public void setDOB(String d)
    {
        this.dob=d;
    }
}
