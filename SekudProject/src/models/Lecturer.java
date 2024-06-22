package models;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Lecturer {
    private int id ;
    private int idDepartment ;
    private int idPerson ;

    public Lecturer(int id, int idDepartment, int idPerson) {
        this.id = id;
        this.idDepartment = idDepartment;
        this.idPerson = idPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }
    
}
