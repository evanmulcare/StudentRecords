package com.mtu.studentrecords.View;
import com.mtu.studentrecords.Controller.ButtonController;
import com.mtu.studentrecords.Model.Student;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.io.Serializable;

public class CreateView extends Dialog<Student> implements Serializable {
    ButtonController btnCtrl = new ButtonController();
    private static TextField studentidField;
    private static TextField fnameField;
    private static TextField lnameField;
    private static TextField emailField;
    private static TextField genderField;
    private static TextField dobField;

    public CreateView()
    {
        super();
        this.setTitle("Add Student");
        this.initStyle(StageStyle.UNDECORATED);
        buildUI();

    }

    private void buildUI()
    {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);

    }
    private Pane createGridPane()
    {
        VBox content = new VBox(10);

        Label studentidLabel = new Label("Student ID");
        Label fnameLabel = new Label("First Name");
        Label lnameLabel = new Label("Last Name");
        Label emailLabel = new Label("Email");
        Label genderLabel = new Label("Gender");
        Label dobLabel = new Label("Date Of Birth");

        studentidField = new TextField();
        fnameField = new TextField();
        lnameField = new TextField();
        emailField = new TextField();
        genderField = new TextField();
        dobField = new TextField();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.add(studentidLabel, 0, 0);
        grid.add(fnameLabel, 0, 1);
        grid.add(lnameLabel, 0, 2);
        grid.add(emailLabel, 0, 3);
        grid.add(genderLabel, 0, 4);
        grid.add(dobLabel, 0, 5);

        grid.add(studentidField, 1, 0);
        GridPane.setHgrow(studentidField, Priority.ALWAYS);

        grid.add(fnameField, 1, 1);
        GridPane.setHgrow(fnameField, Priority.ALWAYS);

        grid.add(lnameField, 1, 2);
        GridPane.setHgrow(lnameField, Priority.ALWAYS);

        grid.add(emailField, 1, 3);
        GridPane.setHgrow(emailField, Priority.ALWAYS);

        grid.add(genderField, 1, 4);
        GridPane.setHgrow(genderField, Priority.ALWAYS);

        grid.add(dobField, 1, 5);
        GridPane.setHgrow(dobField, Priority.ALWAYS);

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");

        save.setOnAction(actionEvent -> {
            btnCtrl.addNewStudent(StudentView.table, StudentView.addList);
            btnCtrl.close(actionEvent);
        });
        save.setPrefSize(150, 20);
        save.setStyle("-fx-background-color: #3e8cc8;-fx-text-fill: white;");

        cancel.setOnAction(actionEvent -> btnCtrl.close(actionEvent));
        cancel.setPrefSize(150, 20);
        cancel.setStyle("-fx-background-color:  #a42f3e;-fx-text-fill: white;");

        grid.add(save, 0, 6);
        grid.add(cancel, 1,6);
        content.getChildren().add(grid);
        return content;

    }
    public static Student getNewStudent() {
        if((studentidField.getText().isEmpty() || fnameField.getText().isEmpty()) || (lnameField.getText().isEmpty()) ||emailField.getText().isEmpty() || genderField.getText().isEmpty() ||  dobField.getText().isEmpty()) {
            return null;
        }
        return new Student(studentidField.getText(), fnameField.getText(), lnameField.getText(), emailField.getText(), genderField.getText(), dobField.getText());
    }

}
