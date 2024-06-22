package models;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Department {
    private int id ;
    private String name ;
    private int idHead ;
    private int idFaculty ;

    public Department(int id, String name, int idHead,
            int idFaculty) {
        this.id = id;
        this.name = name;
        this.idHead = idHead;
        this.idFaculty = idFaculty;
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

    public int getIdHead() {
        return idHead;
    }

    public void setIdHead(int idHead) {
        this.idHead = idHead;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }
        
}
