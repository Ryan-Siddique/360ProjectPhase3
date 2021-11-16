package application;

import java.util.ArrayList;

public class MessageBoard {
    private ArrayList<Message> messages;
    private String msgString;
    
    public MessageBoard() {
       messages = new ArrayList<Message>();
       msgString = "";
    }
    
    public void addMessage(User sender, String subject, String msg) {
        messages.add(new Message(sender, subject, msg));
    }
    
    public String displayMessageBoard() {
        msgString = "";
        for (int i = 0; i < messages.size(); i++) {
            msgString = msgString + messages.get(i).getMessage() + "\n";
      
        }
        return msgString;
    }
    
}
