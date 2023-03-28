package com.mtu.studentrecords.View;
import com.mtu.studentrecords.Controller.ModuleController;
import com.mtu.studentrecords.Model.Module;
import com.mtu.studentrecords.Model.Student;
import javafx.scene.control.*;;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import java.io.Serializable;

public class CreateGradeView extends Dialog<Student> implements Serializable {

    private Module module;
    private Student student;
    private TextField gradeField;
    private ModuleController moduleCtrl = new ModuleController();

    public CreateGradeView(Module module, Student student) {
        super();
        this.module = module;
        this.student = student;
        this.setTitle("Add Grade");
        this.initStyle(StageStyle.UNDECORATED);
        buildUI();
    }

    private void buildUI() {
        Pane pane = createGridPane();
        getDialogPane().setContent(pane);
    }

    private Pane createGridPane() {
        VBox content = new VBox(10);

        Label gradeLabel = new Label("Grade");
        gradeField = new TextField();

        //convert to double
        gradeField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) {
                return change;
            } else {
                return null;
            }
        }));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.add(gradeLabel, 0, 0);

        grid.add(gradeField, 1, 0);
        GridPane.setHgrow(gradeField, Priority.ALWAYS);

        Button save = new Button("Save");
        Button cancel = new Button("Cancel");

        save.setOnAction(actionEvent -> {moduleCtrl.addNewGrade(gradeField, module, student, actionEvent);});
        save.setPrefSize(150, 20);
        save.setStyle("-fx-background-color: #3e8cc8;-fx-text-fill: white;");

        cancel.setOnAction(actionEvent -> moduleCtrl.close(actionEvent));
        cancel.setPrefSize(150, 20);
        cancel.setStyle("-fx-background-color:  #a42f3e;-fx-text-fill: white;");

        grid.add(save, 0, 6);
        grid.add(cancel, 1, 6);
        content.getChildren().add(grid);
        return content;
    }


}




