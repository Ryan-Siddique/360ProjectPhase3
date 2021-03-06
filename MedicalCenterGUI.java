package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;

public class MedicalCenterGUI extends Application {
    
    String userPicked;
    Label label;
    Button submitType;
    ComboBox<String> userOptions;
    ArrayList<Patient> patientList;
    BorderPane pane;
    VBox newpane;
    
    VBox loginPatient; VBox loginPrac;
    Button loginPatientButton; Button loginPracButton;
   
    TabPane tabPane;
    Tab tab1; Tab tab2; Tab tab3; Tab tab4;
    VisitSummaryPane vis; MedicalHistoryPane history; PatientInfoPane info; MessagesPane msgs;
    
    // Practitioner view initializations
    Label idLabel; Label patientSearchLabel; Label newPatientLabel; Label patientListLabel;
    ComboBox<String> patientSearchList;
    TextField patientFirst; TextField patientLast; TextField patientDOB;
    
    Button patientSearchButton; Button addPatientButton;
    
    HBox patientSearchBox; HBox addPatientBox;
    
    Scene selectScene; Scene loginPatientScene; Scene loginPracScene; Scene patientScene; Scene pracScene;
    
  //CHADMAN startLoginPatientScene

    Label welcomeLabel; Label patientSignInLabel; Label practitionerSignInLabel; Label firstNameLabel; Label lastNameLabel; Label dobLabel; Label dontHaveAccountLabel;

    TextField firstNameTextField; TextField lastNameTextField; TextField dobTextField; TextField idTextField;
    GridPane patientLoginPane; GridPane practitionerLoginPane; HBox buttonHBox = new HBox();

    Button createPatientAccountButton; Button signInPatientButton; Button createPracAccountButton; Button signInPracButton;

    BorderPane finalPane;
    String jdbcURL = "jdbc:sqlite:/C:\\sqlite\\sqlite-tools-win32-x86-3360000\\usersDB.db";
    
    Doctor currentDoctor;
    Nurse currentNurse;
    Patient currentPatient;
    public static void main(String[] args) {
        createNewDatabase("users.db");
        createNewTable();
        launch(args);
    }
    
    public void start (Stage stage) throws Exception {
        currentDoctor = new Doctor();
        currentNurse = new Nurse();
        patientList = new ArrayList<Patient>();
        
        // Query to add all patients in database to patient arraylist
        try {
            Connection conn = DriverManager.getConnection(jdbcURL);
            Statement statement = conn.createStatement();
            String selectPatient = "SELECT * FROM Patient";
            statement.executeQuery(selectPatient);
            
            ResultSet result = statement.executeQuery(selectPatient);
            // Test print of results from making SELECT queries
            while (result.next()) {
                Patient newPatient = new Patient();
                newPatient.setFirstName(result.getString("first_name"));
                newPatient.setLastName(result.getString("last_name"));
                newPatient.setDOB(result.getString("dob"));
                patientList.add(newPatient);
                String name = newPatient.getFirstName() + newPatient.getLastName() + newPatient.dob;
                System.out.println(name); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Add patients to combo box so they can be searched for
        patientSearchList = new ComboBox<String>();
        for (int i = 0; i < patientList.size(); i++) {
            patientSearchList.getItems().add(patientList.get(i).toString());
        }
        
        // First window - user chooses their type of user
        pane = new BorderPane();
        userPicked = "";
        label = new Label("Please select one of the following");
        userOptions = new ComboBox<String>();
        userOptions.getItems().add("Doctor");
        userOptions.getItems().add("Nurse");
        userOptions.getItems().add("Patient");
        
        // Once Submit is clicked, user is taken to their respective login windows
        newpane = new VBox(3);
        submitType = new Button("Submit");
        submitType.setOnAction((ActionEvent event) -> {
            ButtonHandler handler = new ButtonHandler();
            handler.handle(event);
            if (userPicked.equals("Patient")) { 
                stage.setScene(startLoginPatientScene());
            } else {
                stage.setScene(startLoginPracScene());
            }
        });
        
        createPatientAccountButton = new Button("Create An Account");
        signInPatientButton = new Button("Sign in");
        createPracAccountButton = new Button("Create An Account");
        signInPracButton = new Button("Sign in");
        
        // Once sign in button is clicked, verify patient exists, get patient's info and display
        signInPatientButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler2 = new ButtonHandler();
            handler2.handle(event);
            String fname = firstNameTextField.getText();
            String lname = lastNameTextField.getText();
            String dob = dobTextField.getText();
            
            try {
                Connection conn = DriverManager.getConnection(jdbcURL);
                PreparedStatement pstmt = conn.prepareStatement("SELECT* from Patient where first_name=? AND last_name=? AND dob=?");
                
                pstmt.setString(1,fname);
                pstmt.setString(2, lname);
                pstmt.setString(3, dob);
                
                ResultSet result = pstmt.executeQuery();
                
                System.out.println("Current patient: " + result.getString("first_name") + result.getString("last_name"));
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            dobTextField.setText("");
            idTextField.setText("");
            stage.setScene(startPatientScene());
        });
        
     // Once button is clicked, display practitioner home page
        signInPracButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler3 = new ButtonHandler();
            handler3.handle(event);
            stage.setScene(startPracScene()); 
            String fname = firstNameTextField.getText();
            String lname = lastNameTextField.getText();
            String dob = dobTextField.getText();
            int id = Integer.parseInt(idTextField.getText());
            try {
                Connection conn = DriverManager.getConnection(jdbcURL);
                PreparedStatement pstmt = conn.prepareStatement("SELECT* from Doctor where first_name=? AND last_name=? AND id=?");
                
                pstmt.setString(1,fname);
                pstmt.setString(2, lname);
                pstmt.setInt(3, id);
                
                ResultSet result = pstmt.executeQuery();
                
                
                System.out.println("Current doc: " + result.getString("first_name") + result.getString("last_name"));
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            dobTextField.setText("");
            idTextField.setText("");
            
        });
        
        // View Patient Profile as a Practitioner
        patientSearchButton = new Button("Select Patient");
        patientSearchButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler4 = new ButtonHandler();
            handler4.handle(event);
            String selectedPatient = patientSearchList.getValue();
            currentPatient = new Patient();
            
            
            stage.setScene(startPatientScene());  
            
        }); 
        
        newpane.getChildren().addAll(label, userOptions, submitType);
        newpane.setAlignment(Pos.CENTER);
        newpane.setSpacing(15);
        pane.setTop(new CustomPane("Welcome to Medical Portal!"));
        pane.setCenter(newpane);
        
        selectScene = new Scene(pane, 900, 600);
        
        stage.setTitle("Sunny Smiles Medical Portal");
        stage.setScene(selectScene);
        stage.show();
    }
    
    public Scene startLoginPatientScene()
    {
        welcomeLabel = new Label("Welcome to Medical Portal!");
        patientSignInLabel = new Label("Patient Sign In:");
        firstNameLabel = new Label("First Name");
        lastNameLabel = new Label("Last Name");
        dobLabel = new Label("Date of birth    ");
        dontHaveAccountLabel = new Label("Don't Have an Account?");

        firstNameTextField = new TextField();
        lastNameTextField = new TextField();
        dobTextField = new TextField();

        patientLoginPane = new GridPane();
        patientLoginPane.add(firstNameLabel, 0, 0);
        patientLoginPane.add(lastNameLabel, 0, 1);
        patientLoginPane.add(dobLabel, 0, 2);
        patientLoginPane.add(firstNameTextField, 1, 0);
        patientLoginPane.add(lastNameTextField, 1, 1);
        patientLoginPane.add(dobTextField, 1, 2);
        patientLoginPane.setVgap(15);

        loginPatient = new VBox();
        loginPatient.setPadding(new Insets(20, 20, 30, 20));
        loginPatient.setSpacing(15);
        loginPatient.getChildren().addAll(welcomeLabel, patientSignInLabel, patientLoginPane, signInPatientButton, buttonHBox);
        loginPatientScene = new Scene(loginPatient, 900, 600);
        buttonHBox.getChildren().addAll(dontHaveAccountLabel, createPatientAccountButton);
        buttonHBox.setSpacing(15);
        loginPatient.setAlignment(Pos.CENTER);
        patientLoginPane.setAlignment(Pos.CENTER);
        buttonHBox.setAlignment(Pos.CENTER);

        return loginPatientScene;
    }
    
    public Scene startLoginPracScene()
    {
        welcomeLabel = new Label("Welcome to Medical Portal!");
        practitionerSignInLabel = new Label("Practitioner Sign In:");
        firstNameLabel = new Label("First Name");
        lastNameLabel = new Label("Last Name");
        dobLabel = new Label("Date of Birth    ");
        idLabel = new Label("ID");
        dontHaveAccountLabel = new Label("Don't Have an Account?");

        firstNameTextField = new TextField();
        lastNameTextField = new TextField();
        dobTextField = new TextField();
        idTextField = new TextField();
        practitionerLoginPane = new GridPane();
        practitionerLoginPane.add(firstNameLabel, 0, 0);
        practitionerLoginPane.add(lastNameLabel, 0, 1);
        practitionerLoginPane.add(dobLabel, 0, 2);
        practitionerLoginPane.add(idLabel, 0, 3);
        practitionerLoginPane.add(firstNameTextField, 1, 0);
        practitionerLoginPane.add(lastNameTextField, 1, 1);
        practitionerLoginPane.add(dobTextField, 1, 2);
        practitionerLoginPane.add(idTextField, 1, 3);
        practitionerLoginPane.setVgap(15);

        loginPrac = new VBox();
        loginPrac.setPadding(new Insets(20, 20, 30, 20));
        loginPrac.setSpacing(15);
        loginPrac.getChildren().addAll(welcomeLabel, practitionerSignInLabel, practitionerLoginPane, signInPracButton, buttonHBox);
        loginPracScene = new Scene(loginPrac, 900, 600);
        buttonHBox.getChildren().addAll(dontHaveAccountLabel, createPracAccountButton);
        buttonHBox.setSpacing(15);
        loginPrac.setAlignment(Pos.CENTER);
        practitionerLoginPane.setAlignment(Pos.CENTER);
        buttonHBox.setAlignment(Pos.CENTER);

        return loginPracScene;
    }

    
    // Displays Patient Profile Scene
    public Scene startPatientScene() {
        tabPane = new TabPane();
        tab1 = new Tab();
        tab2 = new Tab();
        tab3 = new Tab();
        tab4 = new Tab();
        
        // Tab1 = Visit Summary
        tab1.setText("Visit Summary");
        vis = new VisitSummaryPane(userPicked);
        tab1.setContent(vis);
        
        // Tab 2 = Medical History
        tab2.setText("Medical History");
        history = new MedicalHistoryPane(userPicked);
        tab2.setContent(history);
        
        // Tab 3 = Patient Information
        tab3.setText("Patient Information");
        info = new PatientInfoPane(userPicked);
        tab3.setContent(info);
        
        // Tab 4 = Messages
        tab4.setText("Messages");
        msgs = new MessagesPane(userPicked);
        tab4.setContent(msgs);
        
        tabPane.getSelectionModel().select(0);
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
        patientScene = new Scene(tabPane, 900, 600);
        
        return patientScene;
    }
    
    // Practitioner Home page view
    public Scene startPracScene() {
        int id = 20;
        PractitionerHomePane pracpane = new PractitionerHomePane(id);
        pracScene = new Scene(pracpane, 900, 600);
        return pracScene;
    }
    
    
    
    public static void createNewDatabase(String filename) {
        String url = "jdbc:sqlite:C:\\sqlite\\sqlite-tools-win32-x86-3360000" + filename;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Driver name is" + meta.getDriverName());
                System.out.println("New database created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createNewTable() {
        String jdbcURL = "jdbc:sqlite:/C:\\sqlite\\sqlite-tools-win32-x86-3360000\\usersDB.db";
        String patientTable = "CREATE TABLE IF NOT EXISTS Patient(\n"
                + " first_name TEXT, last_name TEXT, dob TEXT, doctorId, nurseId,"
                + " weight INT, height INT, temperature TEXT, blood_pressure TEXT,"
                + " allergies TEXT, concerns TEXT, diagnosis TEXT, notes TEXT," 
                + " prescription_name TEXT, prescription_amt TEXT, prescription_freq TEXT,"
                + " previous_issues TEXT, previous_meds TEXT, immunizations TEXT,"
                + " address TEXT, phone TEXT,"
                + " insurance_company TEXT, insurance_group TEXT, insurance_plan TEXT,\n"
                + " pharmacy_name TEXT, pharmacy_address TEXT\n)";
        
        String doctorTable = "CREATE TABLE IF NOT EXISTS Doctor(\n"
                + " first_name TEXT,\n"
                + " last_name TEXT,\n"
                + " dob TEXT,\n"
                + " id INT PRIMARY KEY\n)";
                
        String nurseTable = "CREATE TABLE IF NOT EXISTS Nurse(\n"
                + " first_name TEXT,\n"
                + " last_name TEXT,\n"
                + " dob TEXT,\n"
                + " id INT PRIMARY KEY\n)";
      
        try {
            Connection conn = DriverManager.getConnection(jdbcURL);
            Statement statement = conn.createStatement();
            statement.execute(patientTable);
            statement.execute(doctorTable);
            statement.execute(nurseTable);
            
            // Insert 2 doctors and 2 nurses
            String insertDoctor = "INSERT OR IGNORE INTO Doctor (first_name, last_name, dob, id) "
                    + "VALUES ('Lisa', 'Song', '02/22/1966', 1010),"
                    + "('Elijah', 'Smith', '11/17/1970', 1011);";
            String insertNurse = "INSERT OR IGNORE INTO Nurse (first_name, last_name, dob, id) "
                    + "VALUES ('Mary', 'Simpson', '08/11/1980', 1012),"
                    + "('Fred', 'Johnson', '12/13/1992', 1013);";
            
            String selectDoctor = "SELECT * FROM Doctor";
            String selectNurse = "SELECT * FROM Nurse";
            
            String delete = "DELETE * FROM Doctor";
            
            statement.executeUpdate(insertDoctor);
            statement.execute(insertNurse);
            statement.executeQuery(selectDoctor);
            statement.executeQuery(selectNurse);
            
            statement.executeUpdate(insertDoctor);
            
            ResultSet result = statement.executeQuery(selectDoctor);
            // Test print of results from making SELECT queries
            while (result.next()) {
                String fname = result.getString("first_name");
                String lname = result.getString("last_name");
                String dob = result.getString("dob");
                int id = result.getInt("id");
                String name = fname + " " + lname + " " + dob + " " + id;
                System.out.println(name); 
            }
            
            ResultSet result2 = statement.executeQuery(selectNurse);
            while (result2.next()) {
                String fname = result2.getString("first_name");
                String lname = result2.getString("last_name");
                String dob = result2.getString("dob");
                int id = result2.getInt("id");
                String name = fname + " " + lname + " " + dob + " " + id;
                System.out.println(name); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                
    }
    
    
    
    class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            userPicked = userOptions.getValue();
        }
    }
    
    public class PractitionerHomePane extends VBox {
        int id;
        public PractitionerHomePane(int id) {
            patientSearchBox = new HBox();
            addPatientBox = new HBox();
            this.id = id;
            idLabel = new Label("ID: " + this.id);
            patientSearchLabel = new Label("Patient Search: ");
            newPatientLabel = new Label("New Patient: ");
            patientListLabel = new Label("Patient List: ");
            
            patientFirst = new TextField("First Name");
            patientLast = new TextField("Last Name");
            patientDOB = new TextField("DOB");
            
            addPatientButton = new Button("Add Patient");
            
            
            
            patientFirst.setOnMouseClicked(e -> {
                patientFirst.setText("");
            });
            patientLast.setOnMouseClicked(e -> {
                patientLast.setText("");
            });
            patientDOB.setOnMouseClicked(e -> {
                patientDOB.setText("");
            });
            
            
            addPatientButton.setOnAction((ActionEvent event) -> {
                ButtonHandler handler = new ButtonHandler();
                handler.handle(event);
                
                try {
                    Connection conn = DriverManager.getConnection(jdbcURL);
                    Statement statement = conn.createStatement();
                    String insertPatient = "INSERT OR IGNORE INTO Patient (first_name, last_name, dob) "
                            + "VALUES ('" + patientFirst.getText() + "', '" + patientLast.getText() + "', '" + patientDOB.getText() + "');";
                    String selectPatient = "SELECT * FROM Patient";
                    
                    statement.executeUpdate(insertPatient);
                    ResultSet result = statement.executeQuery(selectPatient);
                    while (result.next()) {
                        String fname = result.getString("first_name");
                        String lname = result.getString("last_name");
                        String dob = result.getString("dob");
                       
                        String name = fname + " " + lname + " " + dob;
                        
                        patientSearchList.getItems().add(name);
                        
                    }
                } catch (SQLException e) {
                    e.getMessage();
                }
                
                patientFirst.setText("");
                patientLast.setText("");
                patientDOB.setText("");
            });
            
            
            patientSearchBox.getChildren().addAll(patientSearchLabel, patientSearchList, patientSearchButton);
            addPatientBox.getChildren().addAll(newPatientLabel, patientFirst, patientLast, patientDOB, addPatientButton);
            
            patientSearchBox.setSpacing(20);
            addPatientBox.setSpacing(20);
            
            this.getChildren().addAll(idLabel, patientSearchBox, addPatientBox);
            this.setSpacing(30);
            this.setPadding(new Insets(20, 20, 30, 20));
        }
    }
    
}

class CustomPane extends StackPane
{
    public CustomPane(String title)
    {
        this.getChildren().add(new Label(title));
    }
}





