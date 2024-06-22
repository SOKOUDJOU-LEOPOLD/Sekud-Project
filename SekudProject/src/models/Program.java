package models;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Program {
    private int id ;
    private String name ;
    private int idDepartment ;

    public Program(int id, String name, int idDepartment) {
        this.id = id;
        this.name = name;
        this.idDepartment = idDepartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    
}
