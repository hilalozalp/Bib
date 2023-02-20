package model;

public class User {


    private String firstname;
    private String lastname;
    private String userID;
    private String password;



    public User(String firstname, String lastname, String userID, String password){

        this.firstname = firstname;
        this.lastname = lastname;
        this.userID = userID;
        this.password = password;


    }

    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}

