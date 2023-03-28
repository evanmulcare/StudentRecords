package com.mtu.studentrecords.Controller;
import com.mtu.studentrecords.Model.Student;
import com.mtu.studentrecords.View.CreateView;
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
        if (student == null) {
            message("Warning", "Invalid input", "Please fill out all input fields!");
        }
        else {
            addList.add(student);
            populateHandle(table, addList);
        }
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

