package com.mtu.studentrecords.Controller;

import com.mtu.studentrecords.Model.Module;
import com.mtu.studentrecords.Model.Student;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Comparator;
import java.util.List;

public class GradeController {

    public void fillHandle(Student student, GridPane grid, List<Module> moduleList)
    {
        moduleList.sort(Comparator.comparingDouble(module -> -module.getGrade(student.getStudentId())));
        refreshGrid(grid, moduleList, student);
    }

    public void choiceHandle(ChoiceBox filterBox, List<Module> moduleList, Student student)
    {
        String filter = (String) filterBox.getValue();
        switch (filter) {
            case "Sort by highest grade" ->
                    moduleList.sort(Comparator.comparingDouble(module -> -module.getGrade(student.getStudentId())));
            case "Sort by lowest grade" ->
                    moduleList.sort(Comparator.comparingDouble(module -> module.getGrade(student.getStudentId())));
            case "Sort Alphabetically by Module" ->
                    moduleList.sort(Comparator.comparing(Module::getTitle, String.CASE_INSENSITIVE_ORDER));
        }
    }

    public void refreshGrid(GridPane grid, List<Module> moduleList, Student student) {
        // clear the existing grid
        grid.getChildren().clear();

        // add sorted module data
        Label moduleHeader = new Label("Module");
        moduleHeader.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        Label gradeHeader = new Label("Grade");
        gradeHeader.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        GridPane.setHgrow(moduleHeader, Priority.ALWAYS);
        GridPane.setHgrow(gradeHeader, Priority.ALWAYS);

        grid.add(moduleHeader, 0, 0);
        grid.add(gradeHeader, 1, 0);

        int row = 1;
        for (Module module : moduleList) {
            Label moduleLabel = new Label(module.getTitle());
            moduleLabel.setFont(Font.font("Verdana", 20));

            double grade = module.getGrade(student.getStudentId());
            Label gradeLabel = new Label(String.format("%.2f", grade));
            gradeLabel.setFont(Font.font("Verdana", 20));

            GridPane.setHgrow(moduleLabel, Priority.ALWAYS);
            GridPane.setHgrow(gradeLabel, Priority.ALWAYS);

            grid.add(moduleLabel, 0, row);
            grid.add(gradeLabel, 1, row);

            row++;
        }
    }
}
