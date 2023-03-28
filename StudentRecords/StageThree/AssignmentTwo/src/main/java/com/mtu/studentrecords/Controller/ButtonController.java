package com.mtu.studentrecords.Controller;
import com.mtu.studentrecords.Model.Student;
import com.mtu.studentrecords.View.CreateView;
import com.mtu.studentrecords.View.ModuleView;
import com.mtu.studentrecords.View.StudentView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class ButtonController {

    ModuleController moduleCntr = new ModuleController();
    public void loadHandle(ArrayList<Student> addList) {
        loadFile(addList);
        message("Success", "Values Loaded", "The current values have been loaded from the file, use the list button to display the result!");
    }

    public void loadFile(ArrayList<Student> addList) {
        try {
            FileInputStream fileIn = new FileInputStream("studentData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ArrayList<Student> deserializedStudents = (ArrayList<Student>) in.readObject();
            in.close();
            addList.addAll(deserializedStudents);

        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
    }

    public void saveHandle(ArrayList<Student> addList) {

        if (!addList.isEmpty()) {
            try {

                FileOutputStream fileOut = new FileOutputStream("studentData.ser");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

                objectOut.writeObject(addList);
                objectOut.flush();
                objectOut.close();
                System.out.println("successfully saved!");

                message("Success", "Values Saved", "The current values of the List have been saved!");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            message("Warning", "No New Records", "There are no new records to save to file!");
        }
    }

    public void showCreateDialogHandle() {
        // create an empty new student object
        Dialog<Student> createDialog = new CreateView();
        createDialog.showAndWait();
    }

    public void addNewStudent(TableView<Student> table, ArrayList<Student> addList) {
        Student student = CreateView.getNewStudent();
        //INPUT FIELDS NOT FILLED
        if (student != null) {
            addList.add(student);
            populateHandle(table, addList);
        }
    }

    public Student studentValidation(String studentId, String firstName, String lastName, String email, String gender, String dob)
    {
        // Validate student ID
        if(studentId.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||email.isEmpty() ||dob.isEmpty())
        {
            message("Validation Error","Empty Field","All input fields must be filled");
            return null;
        }
        if (studentIdAlreadyExists(studentId)) {
            message("Validation Error","Record Exists","This student is already present in the Database");
            return null;
        }

        // Validate first name
        if ( !firstName.matches("[a-zA-Z]{2,20}")) {
            message("Validation Error","Input Error","First name must be alphabetic and between 2-20 characters");
            return null;
        }

        // Validate last name
        if (!lastName.matches("[a-zA-Z]{2,25}")) {
            message("Validation Error","Input Error","Last name must be alphabetic and between 2-25 characters");
            return null;
        }

        //   Validate email address
        if (!email.matches("\\w+@\\w+\\.\\w+")) {
            message("Validation Error","Input Error","Email must contain an @ and a dot extension (.com, .ie)");
            return null;
        }

        // Validate gender
        if (gender == null) {
            message("Validation Error","No Gender Selected","Must select a gender option");
            return null;
        }

        // Validate date of birth
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            message("Validation Error","Date of Birth format incorrect","Must follow format of 00/00/0000");
            return null;
        }

        return new Student(studentId, firstName, lastName, email, gender, dob);
    }

    private boolean studentIdAlreadyExists(String studentId) {
        // Check if the student ID already exists in your database
        for(Student stu : StudentView.addList)
        {
            if(stu.getStudentId().equals(studentId))
            {
                return true;
            }
        }

        return false;
    }

    public void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void removeHandle(TableView<Student> table, ArrayList<Student> addList) {
        ObservableList<Student> selectedRows, allStudents;
        allStudents = table.getItems();
        selectedRows = table.getSelectionModel().getSelectedItems();

        for (Student student : selectedRows) {
            allStudents.remove(student);
            addList.remove(student);
            return;
        }
        message("Warning", "No value selected.", "Please Select a Row to be deleted!");
    }

    public void exitHandle(ArrayList<Student> addList) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you Sure you wish to Logout");
        alert.setContentText("Do you wish to save before exiting?");

        ButtonType dontSave = new ButtonType("Exit", ButtonBar.ButtonData.NO);
        ButtonType save = new ButtonType("Save and Exit", ButtonBar.ButtonData.YES);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(dontSave, save, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == dontSave) {
            Platform.exit();
            System.exit(0);
        } else if (result.get() == save) {
            saveHandle(addList);
            moduleCntr.saveModuleHandle(ModuleView.addModuleList);

            Platform.exit();
            System.exit(0);
        }
    }

    public void populateHandle(TableView<Student> table, ArrayList<Student> addList) {

        for ( int i = 0; i<table.getItems().size(); i++) {
            table.getItems().clear();
        }

        if (!addList.isEmpty()) {
            // Load objects into table
            for (Student student : addList) {
                table.getItems().add(student);
            }
        } else {
            table.setPlaceholder(new Label("No Student Records to display, click the add button to create a new Student Record."));
        }
    }

    //helper
    private void message(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

