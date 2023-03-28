package com.mtu.studentrecords.Controller;
import com.mtu.studentrecords.Model.Module;
import com.mtu.studentrecords.Model.Student;
import com.mtu.studentrecords.View.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class ModuleController {

    //showing create dialog boxes for Modules and Grades
    public void showCreateModuleDialogHandle() {
        Dialog<Module> createModuleDialog = new CreateModuleView();
        createModuleDialog.showAndWait();
    }
    public void showCreateGradeDialogHandle(Module module, Student student) {
        Dialog<Student> createGradeDialog = new CreateGradeView(module, student);
        createGradeDialog.showAndWait();
    }

    //Adding the Module/grade from the dialog box
    public void addNewModule(ArrayList<Module> addModuleList) {
        Module module = CreateModuleView.getNewModule();
        if (module != null) {
            addModuleList.add(module);
            addNewBox( ModuleView.vbox, ModuleView.hbox, module);
            saveModuleHandle(addModuleList);
        }
    }

    public Module validateModule(String title, String crn)
    {
        // Validate Module, When running tests must comment out the message function

        if (title.isEmpty() || crn.isEmpty()) {
            message("Validation Error","Empty Field","All input fields must be filled");
            return null;
        }
        if (!title.matches("[a-zA-Z]{2,30}")) {
            message("Validation Error","Input Error","Title must be alphabetic and between 2-30 characters");
            return null;
        }

        if (crnAlreadyExists(crn)) {
            message("Validation Error","Record Exists","This crn is already present in the Database");
            return null;
        }

        return new Module(title, crn);
    }

     private boolean crnAlreadyExists(String crn) {
        // Check if the student ID already exists in your database
        for(Module mod : ModuleView.addModuleList)
        {
            if(mod.getCrn().equals(crn))
            {
                return true;
            }
        }

        return false;
    }

    public void addNewGrade(TextField gradeField, Module module, Student student,ActionEvent actionEvent)
    {
        try {
            if (gradeField.getText().isEmpty()) {
                message("Validation Error","Empty Field","All input fields must be filled");
                throw new IllegalArgumentException("Grade field cannot be empty");
            }
            else{
                double grade = Double.parseDouble(gradeField.getText());
                student.grade(module, student.getStudentId(), grade);
                message("Success", "Grade added","grade for student has been added");
                close(actionEvent);

            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid input for grade");
            alert.showAndWait();
        }
    }

    //close the dialog box
    public void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /*used for adding a box to the moduleView, rows of 3, first gets the last row,
    then checks if there are less than 3 boxes, if it is adds the box else it makes anew row then adds the box*/
    public void addNewBox(VBox vbox, HBox hbox, Module module) {
        HBox lastRow = null;

        if (vbox.getChildren().size() > 0) {
            lastRow = (HBox) vbox.getChildren().get(vbox.getChildren().size() - 1);
        }

        if (lastRow != null && lastRow.getChildren().size() < 3) {
            VBox newBox = createNewBox(hbox, module);

            if (!lastRow.getChildren().contains(newBox)) {
                lastRow.getChildren().add(newBox);
            }

        } else {
            HBox newRow = new HBox();
            newRow.setSpacing(10);

            VBox newBox = createNewBox(hbox, module);
            newRow.getChildren().add(newBox);

            HBox.setHgrow(newRow, Priority.ALWAYS);
            vbox.getChildren().add(newRow);
        }
    }


//fill choicebox with current students
    public void listFill(ArrayList<Student> addList, ChoiceBox choiceBox)
    {
        choiceBox.getItems().clear(); // clear the existing items
        choiceBox.getItems().addAll(addList); // add the new items
    }

    //adds boxes on start

    public void populateModuleHandle(ArrayList<Module> addModuleList) {
        loadModuleFile(addModuleList);
        if (!addModuleList.isEmpty()) {

            for (Module module : addModuleList) {
                addNewBox( ModuleView.vbox, ModuleView.hbox, module);
            }
        }
    }

    //save + load methods
    public void saveModuleHandle(ArrayList<Module> addModuleList) {

        if (!addModuleList.isEmpty()) {
            try {

                FileOutputStream fileOut = new FileOutputStream("moduleData.ser");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

                objectOut.writeObject(addModuleList);
                objectOut.flush();
                objectOut.close();
                System.out.println("successfully saved!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadModuleFile(ArrayList<Module> addModuleList) {
        try {
            FileInputStream fileIn = new FileInputStream("moduleData.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            ArrayList<Module> deserializedModules = ( ArrayList<Module>) in.readObject();
            in.close();
            addModuleList.addAll(deserializedModules);

        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
    }



    //helpers methods

    //create a new box for the moduleView
    private VBox createNewBox(HBox hbox, Module module) {

        VBox newBox = new VBox();
        newBox.setId("newBox");

        newBox.setSpacing(10);
        newBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3 - 40);
        newBox.setPrefHeight(300);

        String title = module.getTitle();
        String CRN = module.getCrn();

        // Top half of the box
        VBox topBox = new VBox();
        topBox.setStyle("-fx-background-color: #3e8cc8;");
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(10);
        topBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3 - 40);
        topBox.setPrefHeight(150);

        Text moduleTitle = new Text(title);
        moduleTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        moduleTitle.setFill(Color.WHITE);
        moduleTitle.setTextAlignment(TextAlignment.CENTER);

        Text moduleCRN = new Text(CRN);
        moduleCRN.setFont(Font.font("Verdana", 15));
        moduleCRN.setFill(Color.WHITE);
        moduleCRN.setTextAlignment(TextAlignment.CENTER);

        ChoiceBox<Student> add = new ChoiceBox<>();
        listFill(StudentView.addList, add);

        newBox.setOnMouseClicked(event -> newBox.requestFocus());

        add.setOnMouseClicked(event -> {
            newBox.requestFocus();
            listFill(StudentView.addList, add);

        });

        Button addGrade = new Button("Add Grade");
        addGrade.setStyle("-fx-background-color: #e6b23c;");

        addGrade.setOnAction(actionEvent -> {
            newBox.requestFocus();
            Student student = add.getValue();
            if (student == null) {
                message("error","Invalid Selection", "You Must Select a student to grade");
                return;
            }

            if (module.getGrade(student.getStudentId()) != 0.0) {
                message("Error","Student already has a grade",student.getStudentId() + " is already assigned a grade for " + module.getTitle());
            }
            else
            {
                showCreateGradeDialogHandle(module, student);
            }

        });

        HBox gradeBox = new HBox();
        gradeBox.setAlignment(Pos.TOP_RIGHT);
        gradeBox.getChildren().addAll(addGrade);

        topBox.getChildren().addAll(gradeBox, moduleTitle, moduleCRN);

        // Bottom half of the box
        VBox bottomBox = new VBox();
        bottomBox.setStyle("-fx-background-color: #fff;");
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(10);
        bottomBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3 - 40);
        bottomBox.setPrefHeight(150);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        Button listButton = new Button("Enrolled Students");
        listButton.setOnAction(actionEvent -> {
            newBox.requestFocus();
            StringBuilder sb = new StringBuilder();
            for (Student student : module.getStudents()) {
                sb.append(student.toString());
                sb.append(" ");
                sb.append("\n");
                sb.append("\n");
            }

            message("Enrolled Students", "The list of enrolled students are shown below", sb.toString());

        });

        Button addButton = new Button("Add Student");

        addButton.setOnAction(actionEvent -> {
            newBox.requestFocus();
            Student student = add.getValue();
            if (student == null) {
                message("error","Invalid Selection", "You Must Select a student to add");
                return;
            }

            if (module.getStudents().contains(student)) {
                message("Error","Student is already enrolled",student.getStudentId() + " is already enrolled for " + module.getTitle());
            } else {

                student.addModule(module);
                module.addStudent(student);
                message("Success", "Enrolled", student.getStudentId() + " has been successfully enrolled for " + module.getTitle());
            }
        });

        Button removeButton = new Button("Remove Student");
        removeButton.setStyle("-fx-background-color: #a42f3e;");


        removeButton.setOnAction(actionEvent -> {
            newBox.requestFocus();
            Student student = add.getValue();
            if (student == null) {
                message("error","Invalid Selection", "You Must Select a student to remove");
                return;
            }

            if (!module.getStudents().contains(student)) {
                message("Error","Student is not enrolled",student.getStudentId() + " is not enrolled for " + module.getTitle());
            } else {
                student.removeModule(module);
                module.removeStudent(student);
                message("Success", "Removed", student.getStudentId() + " has been successfully removed from " + module.getTitle());
            }
        });

        buttonBox.getChildren().addAll(listButton, addButton, removeButton);

        bottomBox.getChildren().addAll(buttonBox, add);

        newBox.getChildren().addAll(topBox, bottomBox);
        hbox.getChildren().add(newBox);

        // Add margins and spacing
        VBox.setMargin(topBox, new Insets(10, 10, 0, 10));
        VBox.setMargin(bottomBox, new Insets(0, 10, 10, 10));
        VBox.setMargin(add, new Insets(0, 0, 10, 0));
        HBox.setMargin(addButton, new Insets(0, 10, 0, 0));

        return newBox;
    }


    private void message(String title, String header, String content) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();

    }
}
