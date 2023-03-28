package com.mtu.studentrecords.View;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DashboardView extends VBox {
    public DashboardView() {
        buildUI();
    }

    private void buildUI() {
        HBox top = addHBoxTop();
        getChildren().addAll(top);
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

}
