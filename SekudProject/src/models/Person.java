package models;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Person {
    private int id ;
    private int idLogin ;
    private String lastName ;
    private String firstName ;
    private char gender ;
    private Date dateOfBirth ;
    private String phoneNumber ;
    private String address ;
    private String email ;
    private Image photo ;
    private String pseudo ;
    private String status ;
    private String title ;

    public Person(int id, int idLogin, String lastName, String firstName, char gender, Date dateOfBirth, String phoneNumber, String address, String email, Image photo, String pseudo, String status, String title) {
        this.id = id;
        this.idLogin = idLogin;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.photo = photo;
        this.pseudo = pseudo;
        this.status = status;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
        
}
