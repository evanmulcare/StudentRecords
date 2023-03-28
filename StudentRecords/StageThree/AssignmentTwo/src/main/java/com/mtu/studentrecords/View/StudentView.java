package com.mtu.studentrecords.View;
import com.mtu.studentrecords.Controller.ButtonController;
import com.mtu.studentrecords.Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class StudentView extends VBox {

    public static ArrayList<Student> addList = new ArrayList<>();

    static TableView<Student> table;
    ButtonController btnCtrl = new ButtonController();
    private final Button addButton = new Button("Add Student");
    private final Button removeButton = new Button("Remove Student");
    private final Button saveButton = new Button("Save to File");
    private final Button loadButton = new Button("Load from File");
    private final Button listButton = new Button("List Students");
    public StudentView() {
        buildUI();
    }
    private void buildUI() {
        HBox brow = addHBox();
        brow.setAlignment(Pos.CENTER_RIGHT);
        HBox top = addHBoxTop();
        top.setAlignment(Pos.CENTER_RIGHT);
        TableView t = addTable();
        getChildren().addAll(top, t, brow);
        btnCtrl.loadFile( addList);
        btnCtrl.populateHandle(table,  addList);
    }
    //table to show students in arraylist
    private TableView addTable()
    {
        table = new TableView<>();

        TableColumn<Student, String> columnStudentId = new TableColumn<>("Student ID");
        tableStyle(columnStudentId, "studentId");
        TableColumn<Student, String> columnFirstName = new TableColumn<>("First Name");
        tableStyle(columnFirstName, "fname");
        TableColumn<Student, String> columnLastName = new TableColumn<>("Last Name");
        tableStyle(columnLastName, "lname");
        TableColumn<Student, String> columnEmail = new TableColumn<>("Email");
        tableStyle(columnEmail, "email");
        TableColumn<Student, String> columnGender = new TableColumn<>("Gender");
        tableStyle(columnGender, "gender");
        TableColumn<Student, String> columnDob = new TableColumn<>("DOB");
        tableStyle(columnDob, "dob");

        table.getColumns().add(columnStudentId);
        table.getColumns().add(columnFirstName);
        table.getColumns().add(columnLastName);
        table.getColumns().add(columnEmail);
        table.getColumns().add(columnGender);
        table.getColumns().add(columnDob);

        return table;
    }
    //buttons below table
    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        loadButton.setOnAction(actionEvent -> btnCtrl.loadHandle( addList));
        buttonStyle(loadButton, "-fx-background-color: #e6b23c;");

        saveButton.setOnAction(actionEvent -> btnCtrl.saveHandle( addList));
        buttonStyle(saveButton, "-fx-background-color: #e6b23c;");

        listButton.setOnAction(actionEvent -> btnCtrl.populateHandle(table,  addList));
        buttonStyle(listButton, "-fx-background-color: #e6b23c;");

        hbox.getChildren().addAll(loadButton, saveButton, listButton);

        return hbox;
    }
    //  Header text and add + remove buttons
    private HBox addHBoxTop() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Text Heading = new Text("Students");
        Heading.setTextAlignment(TextAlignment.LEFT);
        Heading.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        Heading.setFill(Color.GRAY);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

       addButton.setOnAction(actionEvent -> btnCtrl.showCreateDialogHandle());
       buttonStyle(addButton, "-fx-background-color: #3e8cc8;");

        removeButton.setOnAction(actionEvent -> btnCtrl.removeHandle(table, addList));
        buttonStyle(removeButton, "-fx-background-color: #a42f3e;");

        hbox.getChildren().addAll(Heading, spacer, addButton, removeButton);
        return hbox;
    }

    //Styling
    private void tableStyle(TableColumn col, String name)
    {
        col.setCellValueFactory(new PropertyValueFactory<>(name));
        col.prefWidthProperty().bind(table.widthProperty().multiply(0.166));
        col.setResizable(false);
    }

    private void buttonStyle(Button btn, String color)
    {
        btn.setPrefSize(100, 20);
        btn.setStyle(color);
    }


}

