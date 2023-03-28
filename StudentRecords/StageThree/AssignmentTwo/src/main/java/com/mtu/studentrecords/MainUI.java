package com.mtu.studentrecords;

import com.mtu.studentrecords.View.SidebarView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class MainUI {
    private Stage stage;
    private SidebarView sidebarView;
    public MainUI() {
        buildUI();
    }

    private void buildUI() {
        stage = new Stage(StageStyle.DECORATED);
        BorderPane root = new BorderPane();

        sidebarView = new SidebarView();

        root.setCenter(sidebarView);
        Scene scene = new Scene(root);

        sidebarView.prefWidthProperty().bind(scene.widthProperty());
        sidebarView.prefHeightProperty().bind(scene.heightProperty());

        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setMaximized(true);
        stage.setTitle("Student Records");
        stage.setScene(scene);
        stage.show();
    }
}
