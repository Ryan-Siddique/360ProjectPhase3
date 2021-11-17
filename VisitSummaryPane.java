package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VisitSummaryPane extends VBox {
    String jdbcURL = "jdbc:sqlite:/C:\\sqlite\\sqlite-tools-win32-x86-3360000\\usersDB.db";
    // Instance variables
    private GridPane vitalGrid;
    private GridPane otherSummary;
    private String userType;
    Label vitalsLabel;
    Label weightLabel;
    Label heightLabel;
    Label tempLabel;
    Label pressureLabel;
    Label allergiesLabel;
    Label concernsLabel;
    Label diagnosisLabel;
    Label prescriptionLabel;
    Label notesLabel;
    
    Text weightText;
    Text heightText;
    Text tempText;
    Text pressureText;
    Text allergiesText;
    Text concernsText;
    Text diagnosisText;
    Text prescriptionText;
    
    TextField weightTextField;
    TextField heightTextField;
    TextField tempTextField;
    TextField pressureTextField;
    TextField allergiesTextField;
    TextField concernsTextField;
    TextField diagnosisTextField;
    TextField notesTextField;
    
    Button saveButton = new Button("Save Changes");
    
    GridPane prescriptionPane;
    Button prescriptionButton;
    Label presNameLabel;
    Label presAmtLabel;
    Label pharmacyLabel;
    TextField presNameTextField;
    TextField presAmtTextField;
    Text pharmacyText;
    VisitSummary visit;
    // VisitSummaryPane constructor
    public VisitSummaryPane(String user) {
        userType = user;
        visit = new VisitSummary();
        vitalsLabel = new Label("Vital Signs");
        weightLabel = new Label("Weight: ");
        heightLabel = new Label("Height: ");
        tempLabel = new Label("Body Temperature: ");
        pressureLabel = new Label("Blood Pressure: ");
        allergiesLabel = new Label("Allergies: ");
        concernsLabel = new Label("Patient Concerns: ");
        diagnosisLabel = new Label("Diagnosis: ");
        prescriptionLabel = new Label("Prescription: ");
        notesLabel = new Label("Notes: ");
        
        // Initializations for Information Display in Patient View
        weightText = new Text();
        heightText = new Text();
        tempText = new Text();
        pressureText = new Text();
        allergiesText = new Text();
        concernsText = new Text();
        diagnosisText = new Text();
        prescriptionText = new Text();
        
        // Initializations for Information Display in Practictioner View
        weightTextField = new TextField();
        heightTextField = new TextField();
        tempTextField = new TextField();
        pressureTextField = new TextField();
        allergiesTextField = new TextField();
        concernsTextField = new TextField();
        diagnosisTextField = new TextField();
        notesTextField = new TextField();
        
        // Initializations for Prescriptions
        prescriptionButton = new Button("Prescribe Medication");
        presNameLabel = new Label("Medication Name: ");
        presAmtLabel = new Label("Medication Amount: ");
        pharmacyLabel = new Label("Pharmacy: ");
        presNameTextField = new TextField();
        presAmtTextField = new TextField();
        pharmacyText = new Text();
        
        // Build view with components that are common between both views
        vitalGrid = new GridPane();
        vitalGrid.add(vitalsLabel, 0, 0);
        vitalGrid.add(weightLabel, 0, 1);
        vitalGrid.add(heightLabel, 0, 2);
        vitalGrid.add(tempLabel, 2, 1);
        vitalGrid.add(pressureLabel, 2, 2);

        otherSummary = new GridPane();
        otherSummary.add(allergiesLabel, 0, 0);
        otherSummary.add(concernsLabel, 0, 2);
       
        
        if (userType.equals("Patient")) {
            patientView();
            this.getChildren().addAll(vitalGrid, otherSummary);
        } else if (userType.equals("Nurse")){
            nurseView();
            this.getChildren().addAll(vitalGrid, otherSummary);
            this.getChildren().add(saveButton);
        } else {
            doctorView();
            this.getChildren().addAll(vitalGrid, otherSummary, prescriptionPane, saveButton);
            
        }
        saveButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler = new ButtonHandler();
            handler.handle(event);
            
            visit.setWeight(Integer.parseInt(weightTextField.getText()));
            visit.setHeight(Integer.parseInt(weightTextField.getText()));
            visit.setBodyTemperature(Integer.parseInt(weightTextField.getText()));
            visit.setBloodPressure(pressureTextField.getText());
            visit.setAllergies(allergiesTextField.getText());
            visit.setPatientConcerns(concernsTextField.getText());
            visit.setDiagnosis(diagnosisTextField.getText());
            visit.setNotes(notesTextField.getText());
            
            int weight = Integer.parseInt(weightTextField.getText());
            int height = Integer.parseInt(weightTextField.getText());
            int temp = Integer.parseInt(weightTextField.getText());
            String bp = pressureTextField.getText();
            String allergies = allergiesTextField.getText();
            String concerns = concernsTextField.getText();
            String diagnosis = diagnosisTextField.getText();
            String notes = notesTextField.getText();
            
            
            try {
                Connection conn = DriverManager.getConnection(jdbcURL);
                
                String insertPatientVisitSum = "INSERT OR IGNORE INTO Patient (weight, height, temperature, blood_pressure,"
                        + "allergies, concerns, diagnosis, notes, prescription_name, prescription_amt, prescription_freq) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?),";
                
                
                PreparedStatement pstmt = conn.prepareStatement(insertPatientVisitSum);
                
                pstmt.setInt(1, weight);
                pstmt.setInt(2, height);
                pstmt.setInt(3, temp);
                pstmt.setString(4, bp);
                pstmt.setString(5, allergies);
                pstmt.setString(6, concerns);
                pstmt.setString(7, diagnosis);
                pstmt.setString(8, notes);
                
                pstmt.executeQuery();
                
              //  System.out.println("Current patient: " + result.getString("first_name") + result.getString("last_name"));
                
                //System.out.println(name); 
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            
        });
        
        // Edit appearance
        this.setPadding(new Insets(20, 20, 30, 20));
        this.setSpacing(15);
        vitalGrid.setVgap(10);
        vitalGrid.setHgap(20);
        
        otherSummary.setVgap(10);
        otherSummary.setHgap(20);
        otherSummary.setPadding(new Insets(20, 0, 20, 0));
        ColumnConstraints halfWidth = new ColumnConstraints();
        halfWidth.setPercentWidth(50);
        otherSummary.getColumnConstraints().addAll(halfWidth, halfWidth);
        
    }
    
    // Called when User is a Patient - displays varying view
    public void patientView() {
        try {
            Connection conn = DriverManager.getConnection(jdbcURL);
            Statement statement = conn.createStatement();
            String selectPatient = "SELECT FROM Patient (weight, height, temperature, blood_pressure,"
                    + "allergies, concerns, diagnosis, notes, prescription_name, prescription_amt, prescription_freq)";
            ResultSet result = statement.executeQuery(selectPatient);
            
            int weight = result.getInt("weight");
            int height = result.getInt("height");
            int temperature = result.getInt("temperature");
            String pressure = result.getString("blood_pressure");
            String allergies = result.getString("allergies");
            String concerns = result.getString("concerns");
            String diagnosis = result.getString("diagnosis");
            String notes = result.getString("notes");
            String prescName = result.getString("prescription_name");
            String prescAmt = result.getString("prescription_amt");
            String prescFreq = result.getString("prescription_freq");
            
            
            visit.setWeight(weight);
            visit.setHeight(height);
            visit.setBodyTemperature(temperature);
            visit.setBloodPressure(pressure);
            visit.setAllergies(allergies);
            visit.setPatientConcerns(concerns);
            visit.setDiagnosis(diagnosis);
            visit.setNotes(notes);
            visit.setMedicationName(prescName);
            visit.setMedicationAmount(prescAmt);
            visit.setMedicationFrequency(prescFreq);
            
            //System.out.println(name); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
            
        weightText.setText(Integer.toString(visit.getWeight()));
        heightText.setText(Integer.toString(visit.getHeight()));
        tempText.setText(Integer.toString(visit.getBodyTemperature()));
        allergiesText.setText(visit.getAllergies());
        concernsText.setText(visit.getPatientConcerns());
        diagnosisText.setText(visit.getDiagnosis());
        prescriptionText.setText("Name: " + visit.getMedicationName() 
        + "\nAmount: " + visit.getMedicationAmount() 
        + "\nFrequency: " + visit.getMedicationFrequency());
        
        vitalGrid.add(weightText, 1, 1);
        vitalGrid.add(heightText, 1, 2);
        vitalGrid.add(tempText, 3, 1);
        vitalGrid.add(pressureText, 3, 2);
        
        otherSummary.add(diagnosisLabel, 1, 0);
        otherSummary.add(prescriptionLabel, 1, 2);
        
        otherSummary.add(allergiesText, 0, 1);
        otherSummary.add(concernsText, 0, 3);
        otherSummary.add(diagnosisText, 1, 1);
        otherSummary.add(prescriptionText, 1, 3);
        
    }
    
    // Called when User is a Nurse/Doctor - displays varying view
    public void nurseView() {
        vitalGrid.add(weightTextField, 1, 1);
        vitalGrid.add(heightTextField, 1, 2);
        vitalGrid.add(tempTextField, 3, 1);
        vitalGrid.add(pressureTextField, 3, 2);
        otherSummary.add(allergiesTextField, 0, 1);
        otherSummary.add(concernsTextField, 0, 3);
        
        otherSummary.add(diagnosisLabel, 1, 0);
        otherSummary.add(notesLabel, 1, 2);
        otherSummary.add(prescriptionLabel, 0, 4);
        
        otherSummary.add(diagnosisTextField, 1, 1);
        otherSummary.add(notesTextField, 1, 3);
        otherSummary.add(prescriptionText, 0, 5);
    }
    
    public void doctorView() {
        vitalGrid.add(weightTextField, 1, 1);
        vitalGrid.add(heightTextField, 1, 2);
        vitalGrid.add(tempTextField, 3, 1);
        vitalGrid.add(pressureTextField, 3, 2);
        otherSummary.add(allergiesTextField, 0, 1);
        otherSummary.add(concernsTextField, 0, 3);
        
        otherSummary.add(diagnosisLabel, 1, 0);
        otherSummary.add(notesLabel, 1, 2);
        
        
        otherSummary.add(diagnosisTextField, 1, 1);
        otherSummary.add(notesTextField, 1, 3);
        
        prescriptionPane = new GridPane();
        prescriptionPane.add(prescriptionLabel, 0, 0);
        prescriptionPane.add(presNameLabel, 0, 1);
        prescriptionPane.add(presAmtLabel, 0, 2);
        prescriptionPane.add(pharmacyLabel, 2, 1);
        prescriptionPane.add(presNameTextField, 1, 1);
        prescriptionPane.add(presAmtTextField, 1, 2);
        prescriptionPane.add(pharmacyText, 3, 1);
        prescriptionPane.add(prescriptionButton, 4, 1);
        prescriptionPane.setVgap(10);
        prescriptionPane.setHgap(20);
        
        
        
    }
}
class ButtonHandler implements EventHandler<ActionEvent> {
    public void handle(ActionEvent event) {
        
    }
}