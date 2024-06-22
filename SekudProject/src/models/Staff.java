package models;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Staff {
    enum Function {HOD,AAO,AO,Bursar,Dean,Rector};
    private int id ;
    private Function function ;
    private int idPerson ;
    private int idLecturer ;

    public Staff(int id, Function function, int idPerson, 
            int idLecturer) {
        this.id = id;
        this.function = function;
        this.idPerson = idPerson;
        this.idLecturer = idLecturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdLecturer() {
        return idLecturer;
    }

    public void setIdLecturer(int idLecturer) {
        this.idLecturer = idLecturer;
    }
    
}
