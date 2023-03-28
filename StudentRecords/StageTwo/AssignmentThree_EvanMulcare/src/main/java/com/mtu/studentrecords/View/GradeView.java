package com.mtu.studentrecords.View;

import com.mtu.studentrecords.Controller.GradeController;
import com.mtu.studentrecords.Controller.ModuleController;
import com.mtu.studentrecords.Model.Module;
import com.mtu.studentrecords.Model.Student;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;


public class GradeView extends VBox {

    public static ChoiceBox<Student> pick = new ChoiceBox<>();
    ModuleController moduleCntrl = new ModuleController();
    GradeController gradeCntrl = new GradeController();

    public GradeView() {
        buildUI();
    }

    private void buildUI() {
        VBox top = addHBoxTop();
        getChildren().addAll(top);
    }

    private VBox addHBoxTop() {
        VBox container = new VBox();

        HBox menu = new HBox();
        menu.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
        menu.setSpacing(10);

        Text heading = new Text("Grades");
        heading.setTextAlignment(TextAlignment.LEFT);
        heading.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        heading.setFill(Color.GRAY);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        pick = new ChoiceBox<>();
        moduleCntrl.listFill(StudentView.addList, pick);

        pick.setOnMouseClicked(event -> moduleCntrl.listFill(StudentView.addList, pick));

        HBox reportContainer = new HBox();

        //leftbox
        VBox left = new VBox();

        left.setSpacing(10);
        left.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 2 - 100);
        left.setPrefHeight(500);
        left.setStyle("-fx-background-color: #fff; -fx-background-radius: 10 0 0 10;");


        Text studentID = new Text("Student ID: ");
        studentID.setFont(Font.font("Verdana", 15));
        studentID.setTextAlignment(TextAlignment.CENTER);

        Pane spacerTwo = new Pane();
        HBox.setHgrow(spacerTwo, Priority.ALWAYS);
        spacerTwo.setMinSize(10, 1);

        //rightbox
        VBox right = new VBox();
        right.setSpacing(10);
        right.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 2 - 100);
        right.setPrefHeight(500);
        right.setStyle("-fx-background-color: #fff; -fx-background-radius: 10 0 0 10;");

        HBox.setMargin(left, new Insets(10, 10, 0, 10));
        HBox.setMargin(right, new Insets(10, 10, 0, 10));
        reportContainer.setVisible(false); // set initial visibility to false

        Button search = new Button("search");
        search.setOnAction(actionEvent -> {
            Student student = pick.getValue();
            if (student == null) {
                System.out.println("Please select a student to add.");
            } else {
                Pane personalGrid = createPersonalPane(student);
                left.getChildren().clear();
                left.getChildren().addAll(personalGrid);

                Pane gradePane = createGradesPane(student);
                right.getChildren().clear();
                right.getChildren().addAll(gradePane);

                reportContainer.setVisible(true); // toggle visibility on
            }
        });

        menu.getChildren().addAll(heading,spacer, pick, search);
        reportContainer.getChildren().addAll(left, spacerTwo, right);

        container.getChildren().addAll(menu, reportContainer);
        return container;
    }

    private Pane createPersonalPane(Student student) {
        VBox content = new VBox(10);

        // create header with different background color
        HBox header = new HBox();
        header.setPrefHeight(50);
        header.setStyle("-fx-background-color: #e6b23c;");
        // add text to header
        Text headerText = new Text("Student Information");
        headerText.setFill(Color.WHITE);
        headerText.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 20));
        HBox.setMargin(headerText, new Insets(10, 0, 0, 10));
        header.getChildren().add(headerText);
        // add header to VBox
        content.getChildren().add(header);

        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(20);

        Label studentidLabel = new Label("Student ID:");
        studentidLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Label fnameLabel = new Label("First Name:");
        fnameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Label lnameLabel = new Label("Last Name:");
        lnameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Label genderLabel = new Label("Gender:");
        genderLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Label dobLabel = new Label("Date of Birth:");
        dobLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        Label studentID = new Label(student.getStudentId());
        studentID.setFont(Font.font("Verdana", 20));
        Label firstName = new Label(student.getFname());
        firstName.setFont(Font.font("Verdana", 20));
        Label lastName = new Label(student.getLname());
        lastName.setFont(Font.font("Verdana", 20));
        Label email = new Label(student.getEmail());
        email.setFont(Font.font("Verdana", 20));
        Label gender = new Label(student.getGender());
        gender.setFont(Font.font("Verdana", 20));
        Label dob = new Label(student.getDob());
        dob.setFont(Font.font("Verdana", 20));

        grid.addRow(0, studentidLabel, studentID);
        grid.addRow(1, fnameLabel, firstName);
        grid.addRow(2, lnameLabel, lastName);
        grid.addRow(3, emailLabel, email);
        grid.addRow(4, genderLabel, gender);
        grid.addRow(5, dobLabel, dob);

        content.getChildren().add(grid);
        return content;
    }


    private Pane createGradesPane(Student student) {
        VBox content = new VBox(10);

        // create header with different background color
        HBox header = new HBox();
        header.setPrefHeight(50);
        header.setStyle("-fx-background-color: #e6b23c;");
        // add text to header
        Text headerText = new Text("Student Grades");
        headerText.setFill(Color.WHITE);
        headerText.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 20));
        HBox.setMargin(headerText, new Insets(10, 0, 0, 10));

        header.getChildren().add(headerText);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        // create filter box
        ChoiceBox<String> filterBox = new ChoiceBox<>();
        filterBox.getItems().addAll("Sort by highest grade", "Sort by lowest grade","Sort Alphabetically by Module");
        filterBox.setValue("Sort by highest grade");
        HBox.setMargin(filterBox, new Insets(5, 10, 0, 0));


        header.getChildren().addAll(spacer, filterBox);

        // add header to VBox
        content.getChildren().add(header);

        if (student.getModules().isEmpty()) {
            Text error = new Text("Currently there are no grades for this student");
            error.setTextAlignment(TextAlignment.LEFT);
            error.setFont(Font.font("Verdana", 20));
            error.setFill(Color.BLACK);
            content.getChildren().add(error);
        } else {
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(5);

            List<Module> moduleList = new ArrayList<>(student.getModules());
            gradeCntrl.fillHandle(student,grid, moduleList);

            filterBox.setOnAction(event -> {
                gradeCntrl.choiceHandle(filterBox, moduleList, student);
                gradeCntrl.refreshGrid(grid, moduleList, student);
            });

            content.getChildren().add(grid);
        }
        return content;
    }
}