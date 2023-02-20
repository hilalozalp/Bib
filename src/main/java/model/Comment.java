package model;

public class Comment {



    private String username;
    private String email;
    private String comment;


    public Comment(String username, String email, String comment) {
        this.username = username;
        this.email = email;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }



}
