package models;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Faculty {
    private int id ;
    private String name ;
    private int idHead ;

    public Faculty(int id, String name, int idHead) {
        this.id = id;
        this.name = name;
        this.idHead = idHead;
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
}
