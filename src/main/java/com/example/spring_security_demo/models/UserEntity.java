package com.example.spring_security_demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String Id;
    private String username;
    private String password;
    private String email;

    public UserEntity() {
    }

    public UserEntity(String Id, String username, String password, String email) {
        this.Id = Id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getId() { return Id; }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String Id) {this.Id = Id;}
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
