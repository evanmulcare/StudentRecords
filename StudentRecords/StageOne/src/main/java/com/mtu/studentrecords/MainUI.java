package com.mtu.studentrecords;

import com.mtu.studentrecords.View.SidebarView;
import com.mtu.studentrecords.View.StudentView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class MainUI {
    private Stage stage;
    private StudentView studentView;
    private SidebarView sidebarView;
    public MainUI() {
        buildUI();
    }

    private void buildUI() {
        stage = new Stage(StageStyle.DECORATED);
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5));

        studentView = new StudentView();
        sidebarView = new SidebarView();

        Insets insets = new Insets(100, 10,20, 10);
        studentView.setPadding(insets);

        root.setCenter(studentView);

        sidebarView.setStyle(" -fx-background-color: #fff;");
        root.setLeft(sidebarView);

        Scene scene = new Scene(root, 320, 240);
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setMaximized(true);
        stage.setTitle("Student Records");
        stage.setScene(scene);
        stage.show();
    }
}
