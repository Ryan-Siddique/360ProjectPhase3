package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PatientInfoPane extends VBox{
    private String userType;
    private HBox contactBox = new HBox();
    private GridPane insurancePane = new GridPane();
    private HBox pharmBox = new HBox();
    
    Button saveButton = new Button("Save Changes");
    Label contactLabel;
    Label contactAddrLabel;
    Label contactPhoneLabel;
    
    Label insuranceLabel;
    Label companyLabel;
    Label groupLabel;
    Label planLabel;
    
    Label pharmLabel;
    Label pharmAddrLabel;
   
    Text contactAddrText;
    Text contactPhoneText;
    
    Text companyText;
    Text groupText;
    Text planText;
    
    Text pharmAddrText;
    
    TextField contactTextField;
    TextField contactAddrTextField;
    TextField contactPhoneTextField;
    
    TextField insuranceTextField;
    TextField companyTextField;
    TextField groupTextField;
    TextField planTextField;
    
    TextField pharmTextField;
    TextField pharmAddrTextField;
    
    public PatientInfoPane(String user) {
        userType = user;
        contactLabel = new Label("Contact Information");
        contactAddrLabel = new Label("Address");
        contactPhoneLabel = new Label("Phone");

        insuranceLabel = new Label("Insurance Information");
        companyLabel = new Label("Company");
        groupLabel = new Label("Group Number");
        planLabel = new Label("Plan");
        
        pharmLabel = new Label("Pharmacy Information");
        pharmAddrLabel = new Label("Address");
        
       
        contactAddrText = new Text();
        contactPhoneText = new Text();
        
        companyText = new Text();
        groupText = new Text();
        planText = new Text();
        
        pharmAddrText = new Text();
        
        contactAddrTextField = new TextField();
        contactPhoneTextField = new TextField();
        
        companyTextField = new TextField();
        groupTextField = new TextField();
        planTextField = new TextField();
        
        pharmAddrTextField = new TextField();
        
        
        contactBox = new HBox();
        insurancePane = new GridPane();
        pharmBox = new HBox();
        
        
        if (userType.equals("Patient")) {
            patientView();
        } else {
            practitionerView();
        }
        this.setPadding(new Insets(20, 20, 30, 20));
        
        this.setSpacing(20);
   
        
        contactBox.setSpacing(15);
        
        insurancePane.setHgap(20);
        insurancePane.setVgap(15);
        pharmBox.setSpacing(20);
        
        
    }
    
    public void patientView() {
        contactBox.getChildren().addAll(contactAddrLabel, contactAddrTextField, contactPhoneLabel, contactPhoneTextField);
        
        insurancePane.add(companyLabel, 0, 0);
        insurancePane.add(companyTextField, 1, 0);
        insurancePane.add(groupLabel, 3, 0);
        insurancePane.add(groupTextField, 4, 0);
        insurancePane.add(planLabel, 3, 1);
        insurancePane.add(planTextField, 4, 1);
        
        pharmBox.getChildren().addAll(pharmAddrLabel, pharmAddrTextField);
        
        this.getChildren().addAll(contactLabel, contactBox,
                insuranceLabel, insurancePane, pharmLabel, pharmBox, saveButton);
    }
    
    public void practitionerView() {
        contactBox.getChildren().addAll(contactAddrLabel, contactAddrText, contactPhoneLabel, contactPhoneText);
        
        insurancePane.add(companyLabel, 0, 0);
        insurancePane.add(companyText, 1, 0);
        insurancePane.add(groupLabel, 3, 0);
        insurancePane.add(groupText, 4, 0);
        insurancePane.add(planLabel, 3, 1);
        insurancePane.add(planText, 4, 1);
        
        pharmBox.getChildren().addAll(pharmAddrLabel, pharmAddrText);
        
        this.getChildren().addAll(contactLabel, contactBox,
                insuranceLabel, insurancePane, pharmLabel, pharmBox);
    }
}
