package database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import models.Course;
import models.CourseSchedule;
import models.Login;
import models.Person;
import models.Student;
import models.Timetable;

/**
 *
 * @author Roger NDJEUMOU
 */
public class DatabaseConnectionManager {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/student_portal?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";

    private Connection connection;

    public DatabaseConnectionManager() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Login checkLogin(Login user) {
        try {
            Statement myStatement = connection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery(
                    "SELECT * FROM login WHERE reg_number='" + user.getRegNumber()
                    + "' AND password=SHA1('" + user.getPassword() + "') ;");
            if (myResultSet.next()) {
                Login newLogin = new Login(
                        myResultSet.getInt("login_id"),
                        myResultSet.getString("reg_number"),
                        myResultSet.getString("password"),
                        myResultSet.getString("login_type")
                );

                myResultSet.close();
                myStatement.close();

                return newLogin;
            }
        } catch (SQLException e) {
            System.out.println("LOGIN MANAGER ERROR: "
                    + e.getMessage());
        }

        return user;
    }

    public String getPseudo(int idLogin) {
        try {
            Statement myStatemt = connection.createStatement();
            ResultSet myRs = myStatemt.executeQuery(
                    "SELECT per_pseudo FROM person WHERE login_id="
                    + idLogin + " ;");
            if (myRs.next()) {
                return myRs.getString("per_pseudo");
            }
        } catch (SQLException e) {
            System.out.println("GET PSEUDO ERROR: " + e.getMessage());
        }

        return "";
    }

    public Person getPersonInfo(int idLogin) {
        String query = "SELECT * FROM `person` WHERE `login_id`= ?";
        try {
            PreparedStatement retrieve = connection.prepareStatement(query);
            retrieve.setInt(1, idLogin);
            ResultSet rs = retrieve.executeQuery();

            if (rs.next()) {
                // Get the profile picture as an image file
                InputStream inStream = rs.getBinaryStream("per_profile_pic");
                Image profilePic = new Image(inStream);
                // Get the other data
                return new Person(
                        rs.getInt("per_id"),
                        rs.getInt("login_id"),
                        rs.getString("per_last_name"),
                        rs.getString("per_first_name"),
                        (rs.getString("per_gender")).charAt(0),
                        rs.getDate("per_DOB"),
                        rs.getString("per_phone_number"),
                        rs.getString("per_address"),
                        rs.getString("per_email"),
                        profilePic,
                        rs.getString("per_pseudo"),
                        rs.getString("per_status"),
                        rs.getString("per_title"));
            }

        } catch (SQLException e) {
            System.out.println("GET PERSON INFO ERROR: " + e.getMessage());
        }

        return null;
    }

    public Student getStudentInfo(String regNumber) {
        String query = "SELECT `stud_id`, `per_id`, `par_id`, "
                + "`prog_id`, `stud_entrance_year`, `stud_level`,"
                + " `stud_status` FROM `student` "
                + "WHERE `stud_id`=?";

        try {
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setString(1, regNumber);
            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {
                return (new Student(
                        rs.getString("stud_id"),
                        rs.getInt("per_id"),
                        rs.getInt("par_id"),
                        rs.getInt("prog_id"),
                        rs.getInt("stud_level"),
                        rs.getInt("stud_entrance_year"),
                        rs.getString("stud_status")));
            }
        } catch (SQLException e) {
            System.out.println("GET STUDENT INFO ERROR: "
                    + e.getMessage());
        }

        return null;
    }

    public ArrayList<Object> getSchedule(int idProgram, int idLevel,
            String day) {

        ArrayList<CourseSchedule> schedules = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        String query = "SELECT `sched_id`, course_schedule.`course_id`, "
                + "`sched_day`, `start_time`, `end_time`, `room`, course.course_title "
                + "FROM `course_schedule` "
                + "JOIN course ON course_schedule.course_id = course.course_id "
                + "JOIN program_course ON course_schedule.course_id "
                + "= program_course.course_id "
                + "WHERE program_course.program_id = ? AND "
                + "program_course.level_id = ? AND "
                + "sched_day = ? "
                + "ORDER BY start_time ASC;";

        try {
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, idProgram);
            prepStatement.setInt(2, idLevel);
            prepStatement.setString(3, day);

            ResultSet rs = prepStatement.executeQuery();
            while (rs.next()) {
                schedules.add(
                        new CourseSchedule(
                                rs.getInt("sched_id"),
                                rs.getString("course_id"),
                                rs.getString("sched_day"),
                                rs.getTime("start_time"),
                                rs.getTime("end_time"),
                                rs.getString("room"))
                );
                courses.add(
                        new Course(
                                rs.getString("course_id"),
                                rs.getString("course_title"))
                );
            }
        } catch (SQLException e) {
            System.out.println("GET SCHEDULE ERROR: "
                    + e.getMessage());
        }

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(schedules);
        objects.add(courses);

        return objects;
    }

    public Timetable getTimetable(int idProgram, String semester, int year) {
        String query = "SELECT `timetable_id`, timetable.`semester_id`, timetable.`depart_id`, `timetable` "
                + "FROM `timetable` "
                + "JOIN semester ON timetable.semester_id = semester.semester_id "
                + "JOIN program ON timetable.depart_id = program.depart_id "
                + "WHERE semester.semester_name = ? AND "
                + "semester.semester_year = ? AND "
                + "program.prog_id = ? ;" ;
        
        try {
            PreparedStatement ps = connection.prepareStatement(query) ;
            ps.setString(1, semester);
            ps.setInt(2, year);
            ps.setInt(3, idProgram);
            
            ResultSet rs = ps.executeQuery() ;
            if (rs.next()){
                try {
                    // Set up a handle to the output file
                    File timetableFile = new File("timetable.pdf") ;
                    FileOutputStream output = new FileOutputStream(timetableFile) ;
                    
                    // Read Blob and store in output file
                    InputStream input = rs.getBinaryStream("timetable") ;
                    byte[] buffer = new byte[10*1024] ;
                    while( input.read(buffer) > 0)
                        output.write(buffer);
                    
                    return (
                            new Timetable(
                                    rs.getInt("timetable_id"), 
                                    rs.getInt("semester_id"), 
                                    rs.getInt("depart_id"), 
                                    timetableFile) 
                            );
                } catch (IOException e){
                    System.out.println("GET TIMETABLE ERROR: " 
                            + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("GET TIMETABLE ERROR: "
                    + e.getMessage());
        }
        
        return null ;
    }
}
