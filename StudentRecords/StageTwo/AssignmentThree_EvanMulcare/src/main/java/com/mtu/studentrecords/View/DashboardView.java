package com.mtu.studentrecords.View;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;


public class DashboardView extends VBox {
    public DashboardView() {
        buildUI();
    }

    private void buildUI() {
        HBox top = addHBoxTop();
        HBox middle = addHBoxMiddle();
        HBox bottom = addHBoxBottom();
        getChildren().addAll(top, middle, bottom);
    }

    private HBox addHBoxTop() {
        HBox hbox = new HBox();
        hbox.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Text heading = new Text("Dashboard");
        heading.setTextAlignment(TextAlignment.LEFT);
        heading.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        heading.setFill(Color.GRAY);
        hbox.getChildren().addAll(heading);

        return hbox;
    }

    private HBox addHBoxMiddle() {
        HBox hbox = new HBox();
        hbox.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        // Create the "AVERAGE GRADE" section
        VBox averageGradeBox = createInlineBox("AVERAGE GRADE", "Soon");
        averageGradeBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        averageGradeBox.setPrefHeight(150);

        // Create the "TOTAL STUDENTS" section
        VBox totalStudentsBox = createInlineBox("TOTAL STUDENTS",  "Soon");
        totalStudentsBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        totalStudentsBox.setPrefHeight(150);

        // Create the "TOTAL MODULES" section
        VBox totalModulesBox = createInlineBox("TOTAL MODULES", "Soon");
        totalModulesBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3);
        totalModulesBox.setPrefHeight(150);

        hbox.getChildren().addAll(averageGradeBox, totalStudentsBox, totalModulesBox);

        return hbox;
    }

    private HBox addHBoxBottom() {
        HBox hbox = new HBox();
        hbox.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        return hbox;
    }

    private VBox createInlineBox(String headingText, String valueText) {
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER); // center align the content of the VBox


        vbox.setStyle("-fx-background-color: #fff;");
        Text heading = new Text(headingText);
        heading.setTextAlignment(TextAlignment.CENTER);
        heading.setFill(Color.GRAY);
        heading.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, 20));

        Text value = new Text(valueText);
        value.setTextAlignment(TextAlignment.CENTER);
        value.setFill(Color.DARKBLUE);
        value.setFont(Font.font("Lucida Sans Unicode", FontWeight.NORMAL, 40));

        vbox.getChildren().addAll(heading, value);
        return vbox;
    }
}
