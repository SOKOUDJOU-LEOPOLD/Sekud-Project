package controllers;

import database.DatabaseConnectionManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.Main;
import models.Course;
import models.CourseSchedule;
import models.Login;
import models.Person;
import models.Student;
import models.Timetable;
import util.MyDate;

/**
 * FXML Controller class
 *
 * @author Roger NDJEUMOU
 */
public class HomeController implements Initializable {

    Login loginInfo;
    Person personalInfo;
    Student studentInfo;
    Timetable timetable;
    ArrayList<CourseSchedule> schedules;
    MyDate dateUtil = new MyDate();
    DatabaseConnectionManager manager
            = new DatabaseConnectionManager();

    @FXML
    private VBox boxClassesToday;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private ImageView userPhoto;

    @FXML
    private Label welcomeLabel;

    @FXML
    void openTimetable(ActionEvent event) {
        (new Main()).viewPDF(timetable.getTimetable());
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get user login info from stage and 
        // use it to retrieve user personal info
        loginInfo = (Login) main.Main.getStage().getUserData();
        personalInfo = manager.getPersonInfo(loginInfo.getId());

        if (loginInfo.getType().equalsIgnoreCase("STUDENT")) {
            studentInfo = manager.getStudentInfo(loginInfo.getRegNumber());
            timetable = manager.getTimetable(studentInfo.getIdProgram(), "Spring", 2022);

            setMyClassesToday();
        }

        setWelcomeLabel();
        setDateTimeLabel();
        setProfilePicture();
    }

    private void setMyClassesToday() {
        ArrayList<Object> objects
                = manager.getSchedule(studentInfo.getIdProgram(),
                        studentInfo.getIdLevel(),
                        dateUtil.getWeekDay());
        
        schedules = (ArrayList<CourseSchedule>) objects.get(0);
        ArrayList<Course> courses = (ArrayList<Course>) objects.get(1);

        if (!schedules.isEmpty()) {
            
            for (int i = 0; i < schedules.size(); i++) {
                CourseSchedule sched = schedules.get(i);
                String content = courses.get(i).getTitle() + " ("
                        + courses.get(i).getIdCourse() + ")";
                String periodAndHall = sched.getStartTime()
                        + " - " + sched.getEndTime() + " in "
                        + sched.getRoom();

                Label classToday = new Label(periodAndHall
                        + " \n\t " + content);

                boxClassesToday.getChildren().add(classToday);
            }
        } else {
            Label noClassLabel = new Label("No class today.") ;
            boxClassesToday.getChildren().add(noClassLabel);
        }
        
        Button btnViewTimetable = new Button("My Timetable");
        btnViewTimetable.setAlignment(Pos.CENTER);
        btnViewTimetable.setOnAction((ActionEvent event) -> {
            openTimetable(event);
        });
        
        StackPane pane = new StackPane(btnViewTimetable) ;
        
        boxClassesToday.getChildren().add(pane) ;
    }

    private void setWelcomeLabel() {
        welcomeLabel.setText("Welcome \""
                + manager.getPseudo(loginInfo.getId()) + "\"");
    }

    private void setDateTimeLabel() {
        dateTimeLabel.setText(dateUtil.toString());
    }

    private void setProfilePicture() {
        userPhoto.setImage(personalInfo.getPhoto());
    }

}
