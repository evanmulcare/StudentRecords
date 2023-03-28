package com.mtu.studentrecords.View;

import com.mtu.studentrecords.Controller.ModuleController;
import com.mtu.studentrecords.Model.Module;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import java.io.Serializable;

public class CreateModuleView extends Dialog<Module> implements Serializable {

    static ModuleController moduleCtrl = new ModuleController();
    private static TextField titleField;
    private static TextField crnField;

    public CreateModuleView()
    {
        super();
        this.setTitle("Add Module");
        this.initStyle(StageStyle.UNDECORATED);
        buildUI();
    }

    private void buildUI()
    {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);

    }
    private Pane createGridPane()
    {
        VBox content = new VBox(10);

        Label titleLabel = new Label("Title");
        Label crnLabel = new Label("CRN");


        titleField = new TextField();
        crnField = new TextField();


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.add(titleLabel, 0, 0);
        grid.add(crnLabel, 0, 1);

        grid.add(titleField, 1, 0);
        GridPane.setHgrow(titleField, Priority.ALWAYS);

        grid.add(crnField, 1, 1);
        GridPane.setHgrow(crnField, Priority.ALWAYS);

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");

        save.setOnAction(actionEvent -> {
            moduleCtrl.addNewModule(ModuleView.addModuleList);
            moduleCtrl.close(actionEvent);
        });

        save.setPrefSize(150, 20);
        save.setStyle("-fx-background-color: #3e8cc8;-fx-text-fill: white;");

        cancel.setOnAction(actionEvent -> moduleCtrl.close(actionEvent));
        cancel.setPrefSize(150, 20);
        cancel.setStyle("-fx-background-color:  #a42f3e;-fx-text-fill: white;");

        grid.add(save, 0, 6);
        grid.add(cancel, 1,6);
        content.getChildren().add(grid);
        return content;

    }
    public static Module getNewModule() {
        String title = titleField.getText();
        String crn = crnField.getText();
        return moduleCtrl.validateModule(title, crn);
    }
}
