package models;

import java.util.Objects;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Student implements Comparable<Student>{
    private String id ;
    private int idPerson ;
    private int idParent ;
    private int idProgram ;
    private int idLevel ;
    private int entranceYear ;
    private String status ;

    public Student(String id, int idPerson, int idParent, 
            int idProgram, int idLevel, int entranceYear, 
            String status) {
        this.id = id;
        this.idPerson = idPerson;
        this.idParent = idParent;
        this.idProgram = idProgram;
        this.idLevel = idLevel;
        this.entranceYear = entranceYear;
        this.status = status;
    }
    
    @Override
    public int compareTo(Student stud){
        return id.compareToIgnoreCase(stud.getId()) ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return Objects.equals(this.id, other.id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
