package handlers;

import database.DatabaseConnectionManager;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import models.AssessmentItem;
import models.LatestAssessment;

/**
 *
 * @author NAOUSSI
 */
public class LatestAssessmentManager {

    private String idStudent;
    DatabaseConnectionManager manager;
    TitledPane assessTitledPane;

    public LatestAssessmentManager(String idStudent) {
        this.idStudent = idStudent;
        this.manager = new DatabaseConnectionManager();
        assessTitledPane = new TitledPane();
        setMyLatestAssessments();
    }

    public TitledPane getAssessTitledPane() {
        assessTitledPane.setCollapsible(false);
        return assessTitledPane;
    }

    public void setMyLatestAssessments() {

        ArrayList<LatestAssessment> latestAssessments
                = manager.getLatestAssessment(idStudent);

        assessTitledPane.setText("My Upcoming Assessments");

        if (!latestAssessments.isEmpty()) {
            TableView<AssessmentItem> table = new TableView<>();
            ObservableList<AssessmentItem> tableData
                    = FXCollections.observableArrayList();

            for (LatestAssessment assessment : latestAssessments) {
                AssessmentItem item = new AssessmentItem(assessment.getTitle(), assessment.getAssesssmentType(),
                        assessment.getCourseTitle(), assessment.getDueDate());

                tableData.add(item);
            }

            TableColumn titleCol = new TableColumn("Title");
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

            TableColumn typeCol = new TableColumn("Type");
            typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

            TableColumn courseCol = new TableColumn("Course");
            courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));

            TableColumn dueDateCol = new TableColumn("Date");
            dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            dueDateCol.setSortable(false);

            table.setItems(tableData);
            table.getColumns().addAll(titleCol, typeCol, dueDateCol, courseCol);

            int rowHeight = 30;
            table.setFixedCellSize(rowHeight);
            tableHeightHelper(table, rowHeight);

            assessTitledPane.setContent(table);

        } else {
            assessTitledPane.setContent(new Label("No Upcoming Assessments."));
        }

    }

    public void tableHeightHelper(TableView<?> table, int rowHeight) {
        table.prefHeightProperty().bind(Bindings.max(2, Bindings.size(table.getItems()))
                .multiply(rowHeight)
                .add(rowHeight));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());
    }

}
