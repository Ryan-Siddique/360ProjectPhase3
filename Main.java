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
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class Main extends Application {
    
    String userPicked;
    Label label;
    Button submitType;
    ComboBox<String> userOptions;
    ArrayList<Patient> patientList;
    BorderPane pane;
    VBox newpane;
    Patient temp;
    VBox loginPatient; VBox loginPrac;
    Button loginPatientButton; Button loginPracButton;
   
    TabPane tabPane;
    Tab tab1; Tab tab2; Tab tab3; Tab tab4;
    VisitSummaryPane vis; MedicalHistoryPane history; PatientInfoPane info; MessagesPane msgs;
    
    // Practitioner view initializations
    Label idLabel; Label patientSearchLabel; Label newPatientLabel; Label patientListLabel;
    ComboBox<Patient> patientSearchList;
    TextField patientFirst; TextField patientLast; TextField patientDOB;
    
    Button patientSearchButton; Button addPatientButton; Button logOutButton;
    
    HBox patientSearchBox; HBox addPatientBox;
    
    Scene selectScene; Scene loginPatientScene; Scene loginPracScene; Scene patientScene; Scene pracScene;
    
  //CHADMAN startLoginPatientScene

    Label welcomeLabel; Label patientSignInLabel; Label practitionerSignInLabel; Label firstNameLabel; Label lastNameLabel; Label dobLabel; Label dontHaveAccountLabel;

    TextField firstNameTextField; TextField lastNameTextField; TextField dobTextField; TextField idTextField;
    GridPane patientLoginPane; GridPane practitionerLoginPane; HBox buttonHBoxPatient; HBox buttonHBoxPrac;

    Button createPatientAccountButton; Button signInPatientButton; Button createPracAccountButton; Button signInPracButton;

    BorderPane finalPane;
    String jdbcURL = "jdbc:sqlite:/C:\\sqlite\\sqlite-tools-win32-x86-3360000\\usersDB.db";
    
    Doctor currentDoctor;
    Nurse currentNurse;
    Patient currentPatient;
    Patient selectedPatient;
    
    ArrayList<Doctor> doctors;
    ArrayList<Nurse> nurses;
    ArrayList<Patient> patients;
    
    public static void main(String[] args) {
        launch(args);
        
    }
    
    public void start (Stage stage) throws Exception {
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        ArrayList<Nurse> nurses = new ArrayList<Nurse>();
        ArrayList<Patient> patients = new ArrayList<Patient>();
        
        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor();
        Nurse nurse1 = new Nurse();
        Nurse nurse2 = new Nurse();
        doctors.add(doctor1);
        doctors.add(doctor2);
        nurses.add(nurse1);
        nurses.add(nurse2);
        
        
        try {
            File doctorFile = new File("C:\\Users\\Han\\eclipse-workspace\\MedCenter\\src\\application\\doctor.txt");
            File nurseFile = new File("C:\\Users\\Han\\eclipse-workspace\\MedCenter\\src\\application\\nurse.txt");
            File patientFile = new File("C:\\Users\\Han\\eclipse-workspace\\MedCenter\\src\\application\\patient.txt");
            
            Scanner scanner = new Scanner(doctorFile);
            
            int count = 0;
            String str;
            String[] data = new String[5];
           
            str = scanner.nextLine();
            data = str.split(",");
            
            (doctors.get(0)).setFirstName(data[1]);
                (doctors.get(0)).setLastName(data[2]);
                (doctors.get(0)).setDOB(data[3]);
                (doctors.get(0)).setID(Integer.parseInt(data[4]));
                
                str = scanner.nextLine();
                data = str.split(",");
               
                (doctors.get(1)).setFirstName(data[1]);
                (doctors.get(1)).setLastName(data[2]);
                (doctors.get(1)).setDOB(data[3]);
                (doctors.get(1)).setID(Integer.parseInt(data[4]));

            
            scanner.close();
            
            scanner = new Scanner(nurseFile);
            count = 0;
            
                str = scanner.nextLine();
                data = str.split(",");
                nurses.get(count).setFirstName(data[1]);
                nurses.get(count).setLastName(data[2]);
                nurses.get(count).setDOB(data[3]);
                nurses.get(count).setID(Integer.parseInt(data[4]));
                count++;
            
            scanner.close();
          
            scanner = new Scanner(patientFile);
            count = 0;
           
                str = scanner.nextLine();
                data = str.split(",");
                Patient newPatient = new Patient();
                patients.add(newPatient);
                patients.get(0).setFirstName(data[1]);
                patients.get(0).setLastName(data[2]);
                patients.get(0).setDOB(data[3]);
                patients.get(0).setID(Integer.parseInt(data[4]));
                
           
            
         } catch (FileNotFoundException e) {
             System.out.println("Error occurred");
         }
        // Add patients to combo box so they can be searched for
        
        patientSearchList = new ComboBox<Patient>();
        for (int i = 0; i < patients.size(); i++) {
            patientSearchList.getItems().add(patients.get(i));
        }
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
            
            temp = new Patient();
            for (int i = 0; i < patients.size(); i++) {
                if (fname.equals(patients.get(i).getFirstName()) && lname.equals(patients.get(i).getLastName())) {
                    selectedPatient = patients.get(i);
                }
            }
           
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            dobTextField.setText("");
            
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
            selectedPatient = patientSearchList.getValue();
            
            VisitSummaryPane visit = new VisitSummaryPane(userPicked, selectedPatient, logOutButton);
                   
            stage.setScene(startPatientScene());  
            
        }); 
        
        logOutButton = new Button("Log Out");
        logOutButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler = new ButtonHandler();
            handler.handle(event);
            stage.setScene(pickTypeScene());
            
        });
        
        
        
        stage.setTitle("Sunny Smiles Medical Portal");
        stage.setScene(pickTypeScene());
        stage.show();
    }
    
    public Scene pickTypeScene() {
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
        
        newpane.getChildren().addAll(label, userOptions, submitType);
        newpane.setAlignment(Pos.CENTER);
        newpane.setSpacing(15);
        pane.setTop(new CustomPane("Welcome to Medical Portal!"));
        pane.setCenter(newpane);
        
        selectScene = new Scene(pane, 900, 600);
        return selectScene;
    }
    
    public Scene startLoginPatientScene()
    {
        welcomeLabel = new Label("Welcome to Medical Portal!");
        patientSignInLabel = new Label("Patient Sign In:");
        firstNameLabel = new Label("First Name");
        lastNameLabel = new Label("Last Name");
        dobLabel = new Label("Date of birth    ");
        dontHaveAccountLabel = new Label("Don't Have an Account?");
        buttonHBoxPatient = new HBox();
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
        buttonHBoxPatient.getChildren().addAll(dontHaveAccountLabel, createPatientAccountButton);
        loginPatient.getChildren().addAll(welcomeLabel, patientSignInLabel, patientLoginPane, signInPatientButton, buttonHBoxPatient);
        loginPatientScene = new Scene(loginPatient, 900, 600);
        
        buttonHBoxPatient.setSpacing(15);
        loginPatient.setAlignment(Pos.CENTER);
        patientLoginPane.setAlignment(Pos.CENTER);
        buttonHBoxPatient.setAlignment(Pos.CENTER);

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
        buttonHBoxPrac = new HBox();
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
        buttonHBoxPrac.getChildren().addAll(dontHaveAccountLabel, createPracAccountButton);
        loginPrac.getChildren().addAll(welcomeLabel, practitionerSignInLabel, practitionerLoginPane, signInPracButton, buttonHBoxPrac);
        loginPracScene = new Scene(loginPrac, 900, 600);
        buttonHBoxPrac.setSpacing(15);
        loginPrac.setAlignment(Pos.CENTER);
        practitionerLoginPane.setAlignment(Pos.CENTER);
        buttonHBoxPrac.setAlignment(Pos.CENTER);

        return loginPracScene;
    }

    
    // Displays Patient Profile Scene
    /**
     * @param patient
     * @return
     */
    public Scene startPatientScene() {
        tabPane = new TabPane();
        tab1 = new Tab();
        tab2 = new Tab();
        tab3 = new Tab();
        tab4 = new Tab();
        
        // Tab1 = Visit Summary
        tab1.setText("Visit Summary");
        vis = new VisitSummaryPane(userPicked, selectedPatient, logOutButton);
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
        int id = 1010;
        PractitionerHomePane pracpane = new PractitionerHomePane(id);
        pracScene = new Scene(pracpane, 900, 600);
        return pracScene;
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
                Patient newPatient = new Patient();
                
                newPatient.setFirstName(patientFirst.getText());
                newPatient.setLastName(patientLast.getText());
                newPatient.setDOB(patientDOB.getText());
                patients.add(newPatient);
                
                patientFirst.setText("");
                patientLast.setText("");
                patientDOB.setText("");
            });
            
            
            
            patientSearchBox.getChildren().addAll(patientSearchLabel, patientSearchList, patientSearchButton);
            addPatientBox.getChildren().addAll(newPatientLabel, patientFirst, patientLast, patientDOB, addPatientButton);
            
            patientSearchBox.setSpacing(20);
            addPatientBox.setSpacing(20);
            
            this.getChildren().addAll(idLabel, patientSearchBox, addPatientBox, logOutButton);
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





