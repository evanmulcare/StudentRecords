package com.mtu.studentrecords.View;
import com.mtu.studentrecords.Controller.ButtonController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SidebarView extends VBox {
    ButtonController btnCtrl = new ButtonController();
    public SidebarView(){
        buildUI();
    }
    private void buildUI() {
        HBox top = addHBoxTop();
        HBox middle = addHBoxMiddle();
        HBox exit = addHBoxBottom();
        top.setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(top, middle, exit);
    }

    private HBox addHBoxTop() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Text Heading = new Text("Student Records");
        Heading.setTextAlignment(TextAlignment.CENTER);
        Heading.setFont(Font.font("Verdana", 20));
        Heading.setFill(Color.BLACK);

        hbox.getChildren().addAll(Heading);
        return hbox;
    }

    private HBox addHBoxMiddle() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(250, 0, 0, 100));
        hbox.setMinHeight(600);
        Text Heading = new Text("Coming Soon");
        Heading.setFont(Font.font("Verdana", 15));
        hbox.getChildren().addAll(Heading);
        return hbox;
    }
    private HBox addHBoxBottom() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Button buttonExit = new Button("EXIT");
        buttonExit.setPrefSize(300, 80);
        buttonExit.setStyle("-fx-background-color: #a42f3e;");

        buttonExit.setOnAction(event -> btnCtrl.exitHandle(StudentView.addList));
        hbox.getChildren().addAll(buttonExit);
        return hbox;
    }

}