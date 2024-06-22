package models;

import java.io.File;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Timetable {
    private int id ;
    private int idSemester ;
    private int idDepartment ;
    private File timetable ;

    public Timetable(int id, int idSemester, int idDepartment, File timetable) {
        this.id = id;
        this.idSemester = idSemester;
        this.idDepartment = idDepartment;
        this.timetable = timetable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(int idSemester) {
        this.idSemester = idSemester;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public File getTimetable() {
        return timetable;
    }

    public void setTimetable(File timetable) {
        this.timetable = timetable;
    }
    
}
