package application;

public class Message {
    private String text;
    private String subject;
    private User sender;
    
    public Message(User sender, String subject, String text) {
        this.text = text;
        this.subject = subject;
        this.sender = sender;
    }
    
    public String getMessage() {
       
        return "From: " + this.getSender(sender) + " Subject: " + this.subject + " Message: " + this.text;
    }
    
    public String getSender(User sender) {
        return sender.getFirstName() + sender.getLastName();
    }
    
}
