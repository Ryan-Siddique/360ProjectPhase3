package com.example.phase3test2;

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
import javafx.scene.control.TextField;


public class MedicalCenterGUI extends Application {
    
    String userPicked;
    Label label;
    Button submitType;
    ComboBox<String> userOptions;
    BorderPane pane;
    VBox newpane;
    
    VBox loginPatient;
    VBox loginPrac;
    Button loginPatientButton;
    Button loginPracButton;
   
    TabPane tabPane;
    Tab tab1;
    Tab tab2;
    Tab tab3;
    Tab tab4;
    VisitSummaryPane vis;
    MedicalHistoryPane history;
    PatientInfoPane info;
    MessagesPane msgs;
    
    // Practitioner view initializations
    Label idLabel;
    Label patientSearchLabel;
    Label newPatientLabel;
    Label patientListLabel;
    ComboBox<String> patientSearchList;
    TextField patientFirst;
    TextField patientLast;
    TextField patientDOB;
    Label PatientSignIn;
    
    Button patientSearchButton;
    Button addPatientButton;
    
    HBox patientSearchBox;
    HBox addPatientBox;
    
    Scene selectScene;
    Scene loginPatientScene;
    Scene loginPracScene;
    Scene patientScene;
    Scene pracScene;

    //CHADMAN startLoginPatientScene

    Label welcomeLabel;
    Label patientSignInLabel;
    Label practitionerSignInLabel;
    Label firstNameLabel;
    Label lastNameLabel;
    Label dobLabel;
    Label dontHaveAccountLabel;

    TextField firstNameTextField;
    TextField lastNameTextField;
    TextField dobTextField;
    TextField idTextField;
    GridPane patientLoginPane;
    GridPane practitionerLoginPane;
    HBox buttonHBox = new HBox();

    Button createAccountButton;
    Button signInButton;

    BorderPane finalPane;

    public void start (Stage stage) throws Exception {
        pane = new BorderPane();
        userPicked = "";
        label = new Label("Please select one of the following");
        userOptions = new ComboBox<String>();
        userOptions.getItems().add("Doctor");
        userOptions.getItems().add("Nurse");
        userOptions.getItems().add("Patient");

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
        
        loginPatientButton = new Button("Log in as Patient");
        loginPracButton = new Button("Log in as Practitioner");
        loginPatientButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler2 = new ButtonHandler();
            handler2.handle(event);
            stage.setScene(startPatientScene());  
            
        });
        
        loginPracButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler3 = new ButtonHandler();
            handler3.handle(event);
            stage.setScene(startPracScene());  
            
        });
        
        // View Patient Profile as a Practitioner
        patientSearchButton = new Button("Select Patient");
        patientSearchButton.setOnAction((ActionEvent event) -> {
            ButtonHandler handler4 = new ButtonHandler();
            handler4.handle(event);
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

        createAccountButton = new Button("Create An Account");
        signInButton = new Button("Sign in");

        patientLoginPane = new GridPane();
        patientLoginPane.add(firstNameLabel, 0, 0);
        patientLoginPane.add(lastNameLabel, 0, 1);
        patientLoginPane.add(dobLabel, 0, 2);
        patientLoginPane.add(firstNameTextField, 1, 0);
        patientLoginPane.add(lastNameTextField, 1, 1);
        patientLoginPane.add(dobTextField, 1, 2);

        loginPatient = new VBox();
        loginPatient.setPadding(new Insets(20, 20, 30, 20));
        loginPatient.setSpacing(15);
        loginPatient.getChildren().addAll(welcomeLabel, patientSignInLabel, patientLoginPane, signInButton, buttonHBox);
        loginPatientScene = new Scene(loginPatient, 900, 600);
        buttonHBox.getChildren().addAll(dontHaveAccountLabel, createAccountButton);
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
        createAccountButton = new Button("Create An Account");
        signInButton = new Button("Sign in");
        practitionerLoginPane = new GridPane();
        practitionerLoginPane.add(firstNameLabel, 0, 0);
        practitionerLoginPane.add(lastNameLabel, 0, 1);
        practitionerLoginPane.add(dobLabel, 0, 2);
        practitionerLoginPane.add(idLabel, 0, 3);
        practitionerLoginPane.add(firstNameTextField, 1, 0);
        practitionerLoginPane.add(lastNameTextField, 1, 1);
        practitionerLoginPane.add(dobTextField, 1, 2);
        practitionerLoginPane.add(idTextField, 1, 3);

        loginPrac = new VBox();
        loginPrac.setPadding(new Insets(20, 20, 30, 20));
        loginPrac.setSpacing(15);
        loginPrac.getChildren().addAll(welcomeLabel, practitionerSignInLabel, practitionerLoginPane, signInButton, buttonHBox);
        loginPracScene = new Scene(loginPrac, 900, 600);
        buttonHBox.getChildren().addAll(dontHaveAccountLabel, createAccountButton);
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
    
    public static void main(String[] args) {
        launch(args);
    }
    
    class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            userPicked = userOptions.getValue();
            System.out.println(userPicked);
         
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
            patientSearchList = new ComboBox<String>();
            patientFirst = new TextField("First Name");
            patientLast = new TextField("Last Name");
            patientDOB = new TextField("DOB");
            
            addPatientButton = new Button("Add Patient");
            
            patientSearchList.getItems().add("John Rose");
            
            patientFirst.setOnMouseClicked(e -> {
                patientFirst.setText("");
            });
            patientLast.setOnMouseClicked(e -> {
                patientLast.setText("");
            });
            patientDOB.setOnMouseClicked(e -> {
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





