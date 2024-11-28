package it.uniroma2.ispw.model.login;

import it.uniroma2.ispw.enums.Role;

public class LoginModel {

    private String email;

    private String password;

    private Role role;
    public LoginModel(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public LoginModel() {

    }

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
