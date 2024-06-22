package models;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Login {
    private int id ;
    private String regNumber ;
    private String password ;
    private String type ;

    public Login(int id, String regNumber, String password, 
            String type) {
        this.id = id;
        this.regNumber = regNumber;
        this.password = password;
        this.type = type;
    }

    public Login(String regNumber, String password) {
        this.id = 0 ;
        this.regNumber = regNumber;
        this.password = password;
        this.type = "" ;
    }
       
    public boolean isCorrectLogin(Login l){
        return (regNumber.equalsIgnoreCase(l.regNumber) &&
                password.equals(l.password)) ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
