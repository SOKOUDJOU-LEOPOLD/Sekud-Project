package models;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Parent {
    private int id ;
    private String name ;
    private String phoneNumber ;
    private String email ;

    public Parent(int id, String name, String phoneNumber, 
            String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Parent(String name, String phoneNumber, String email) {
        this.id = 0 ;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
