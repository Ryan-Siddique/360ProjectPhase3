package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MessagesPane extends VBox{
    private String userType;
    private HBox messageTags;
    
    Label msgLabel;
    Label createMsgLabel;
    TextArea messagesTextArea;
    TextField recipientTextField;
    TextField subjectTextField;
    TextArea newMsgTextArea;
    Button sendButton;
    
    public MessagesPane(String user) {
        userType = user;
        msgLabel = new Label("Messages");
        createMsgLabel = new Label("Create New Message");
        messagesTextArea = new TextArea();
        recipientTextField = new TextField("To: ");
        subjectTextField = new TextField("Subject: ");
        newMsgTextArea = new TextArea("Message...");
        sendButton = new Button("Send Message");
        
        recipientTextField.setOnMouseClicked(e -> {
            recipientTextField.setText("");
        });
        subjectTextField.setOnMouseClicked(e -> {
            subjectTextField.setText("");
        });
        newMsgTextArea.setOnMouseClicked(e -> {
            newMsgTextArea.setText("");
        });
        
        messageTags = new HBox();
        messageTags.getChildren().addAll(recipientTextField, subjectTextField, sendButton);
        messageTags.setSpacing(10);
        
        this.getChildren().addAll(msgLabel, messagesTextArea, createMsgLabel, messageTags, newMsgTextArea);
        this.setPadding(new Insets(20, 20, 30, 20));
        this.setSpacing(15);
    }
    
}
