package com.mtu.studentrecords.View;

import com.mtu.studentrecords.Controller.ModuleController;
import com.mtu.studentrecords.Model.Module;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import java.util.ArrayList;

public class ModuleView extends VBox {
    public static ArrayList<Module> addModuleList = new ArrayList<>();
    private final ModuleController moduleCtrl = new ModuleController();
    public static VBox vbox = new VBox();
    public static HBox hbox = new HBox();

    public ModuleView() {
        buildUI();
    }
    private void buildUI() {
        HBox top = addHBoxTop();
        ScrollPane moduleList = modulePane();
        getChildren().addAll(top, moduleList);
        moduleCtrl.populateModuleHandle(addModuleList);
    }


    private HBox addHBoxTop() {
        HBox hbox = new HBox();
        hbox.setPadding(new javafx.geometry.Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Text heading = new Text("Modules");
        heading.setTextAlignment(TextAlignment.LEFT);
        heading.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        heading.setFill(Color.GRAY);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);

        hbox.getChildren().addAll(heading, spacer);

        return hbox;
    }
    public ScrollPane modulePane() {
        // Create the top half of the box
        VBox topBox = new VBox();
        topBox.setStyle("-fx-background-color: #3e8cc8;");
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_CENTER);
        topBox.setSpacing(10);
        topBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3 - 40);
        topBox.setPrefHeight(150);

        Text moduleTitle = new Text("Create A Module");
        moduleTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        moduleTitle.setTextAlignment(TextAlignment.CENTER);
        moduleTitle.setFill(Color.WHITE);

        topBox.getChildren().addAll(moduleTitle);

        // Create the bottom half of the box
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

        Button createButton = new Button("Create Module");

        buttonBox.getChildren().addAll(createButton);
        bottomBox.getChildren().addAll(buttonBox);

        // Add margins and spacing
        VBox newBox = new VBox(topBox, bottomBox);
        newBox.setSpacing(10);
        newBox.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3 - 40);
        newBox.setPrefHeight(300);

        VBox.setMargin(topBox, new Insets(10, 10, 0, 10));
        VBox.setMargin(bottomBox, new Insets(0, 10, 10, 10));
        HBox.setMargin(createButton, new Insets(0, 10, 0, 0));

        createButton.setOnAction(e -> moduleCtrl.showCreateModuleDialogHandle());

        // Create the main layout
        vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().add(newBox);
        vbox.getChildren().add(hbox);

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight());

        return scrollPane;
    }
}

