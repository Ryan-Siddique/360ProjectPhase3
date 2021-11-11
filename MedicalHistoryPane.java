package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MedicalHistoryPane extends VBox{
    private String userType;
    Label prevIssueLabel;
    Label prevMedLabel;
    Label immunizationsLabel;
    
    TextField prevIssueTextField;
    TextField prevMedTextField;
    TextField immunizationsTextField;
    
    Text prevIssueText;
    Text prevMedText;
    Text immunizationsText;
    
    Button saveHealthButton;
    Button saveMedButton;
    Button saveImmuButton;
    
    public MedicalHistoryPane(String user) {
        userType = user;
        prevIssueLabel = new Label("Previous Health Issues: ");
        prevMedLabel = new Label("Previously Prescribed Medications: ");
        immunizationsLabel = new Label("History of Immunizations: ");
        
        prevIssueTextField = new TextField();
        prevMedTextField = new TextField();
        immunizationsTextField = new TextField();
        
        prevIssueText = new Text();
        prevMedText = new Text();
        immunizationsText = new Text();
        
        saveHealthButton = new Button("Save Changes");
        saveMedButton = new Button("Save Changes");
        saveImmuButton = new Button("Save Changes");
        
        if (userType.equals("Patient")) {
            patientView();
        } else {
            practitionerView();
        }
        
     // Edit appearance
        this.setPadding(new Insets(20, 20, 30, 20));
        this.setSpacing(20);
        
        
    }
    
    public void patientView() {
        this.getChildren().addAll(prevIssueLabel, prevIssueText,
                prevMedLabel, prevMedText,
                immunizationsLabel, immunizationsText);
        
    }
    
    public void practitionerView() {
        this.getChildren().addAll(prevIssueLabel, prevIssueTextField, saveHealthButton,
                prevMedLabel, prevMedTextField, saveMedButton,
                immunizationsLabel, immunizationsTextField, saveImmuButton);
    }
    
}
