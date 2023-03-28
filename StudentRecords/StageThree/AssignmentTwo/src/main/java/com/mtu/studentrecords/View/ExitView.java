package com.mtu.studentrecords.View;

import com.mtu.studentrecords.Controller.ButtonController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ExitView extends VBox {

    ButtonController btnCtrl = new ButtonController();

    public ExitView() {
        buildUI();
    }

    private void buildUI() {
        VBox vbox = new VBox();

        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        Text title = new Text("Are you sure you want to exit?");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Lucida Sans Unicode", FontWeight.MEDIUM, 40));
        title.setFill(Color.GRAY);

        Button buttonExit = new Button("EXIT");
        buttonExit.setPrefSize(300, 80);
        buttonExit.setStyle("-fx-background-color: #a42f3e;");
        buttonExit.setOnAction(event -> btnCtrl.exitHandle(StudentView.addList));

        vbox.getChildren().addAll(title, buttonExit);

        VBox.setMargin(vbox, new Insets(200, 0, 20, 0)); // set margin at top

        getChildren().add(vbox);
    }
}