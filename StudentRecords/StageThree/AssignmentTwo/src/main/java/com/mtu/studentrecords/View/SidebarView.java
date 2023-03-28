package com.mtu.studentrecords.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
public class SidebarView extends VBox {
    public SidebarView(){
        buildUI();
    }
    private void buildUI() {
        HBox top = addHBoxTop();
        TabPane middle = addHBoxMiddle();
        top.setAlignment(Pos.TOP_LEFT);
        getChildren().addAll(top, middle);
    }

    private HBox addHBoxTop() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: white;");

        // create an image view for the heading
        ImageView headingImage = new ImageView(new Image(getClass().getResourceAsStream("/images/MTU LOGO.png")));
        headingImage.setFitWidth(50);
        headingImage.setFitHeight(50);


        Text Heading = new Text("Student Records");
        Heading.setTextAlignment(TextAlignment.CENTER);
        Heading.setFont(Font.font("Open Sans", 20));
        Heading.setFill(Color.GRAY);

        // add margin to the left of the image
        HBox.setMargin(headingImage, new Insets(0, 0, 0, 20));
        HBox.setMargin(Heading, new Insets(0, 0, 0, 620));


        hbox.getChildren().addAll(headingImage, Heading);
        return hbox;
    }

    private TabPane addHBoxMiddle() {
        TabPane tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);

        Tab dashtab = new Tab("Dashboard");
        dashtab.setClosable(false);
        DashboardView dashboardView = new DashboardView();
        dashtab.setContent(dashboardView);

        Tab studenttab = new Tab("Students");
        studenttab.setClosable(false);
        StudentView studentView = new StudentView();
        studenttab.setContent(studentView);

        Tab moduletab = new Tab("Modules");
        moduletab.setClosable(false);
        ModuleView moduleView = new ModuleView();
        moduletab.setContent(moduleView);

        Tab infotab = new Tab("Records");
        infotab.setClosable(false);
        GradeView gradeView = new GradeView();
        infotab.setContent(gradeView);

        Tab exittab = new Tab("Exit");
        exittab.setClosable(false);
        ExitView exitView = new ExitView();
        exittab.setContent(exitView);

        tabPane.getTabs().addAll(dashtab, studenttab, moduletab, infotab, exittab);

        return tabPane;
    }

}