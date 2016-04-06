package com.example.david.brokeorbroker;




import java.io.Serializable;

/**
 * Created by David on 2/26/2016.
 */
@SuppressWarnings("serial")
public class User implements Serializable {
    private String name;
    private String email;
    private String username;
    private String password;

    public User() {
        name = "";
        email = "";
        username = "";
        password = "";
    }

    public User(String username) {

        this.username = username;

    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;

    }
    public User(String name, String email, String username) {

        this.name = name;
        this.email = email;
        this.username = username;

    }

    public User(String name, String email, String username, String password) {

        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    //LogIn Method
    public void login(BackgroundTask b) {
        String method = "login";
        b.execute(method, username, password);
    }

    //Logout Method
    public void logout() {
        name = "";
        email = "";
        password = "";
        username = "";
    }

    //createAccount method
    public void createAccount(BackgroundTask b) {

        String method = "register";
        b.execute(method, this.name, this.email, this.username, this.password);
    }


}
