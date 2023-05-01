package com.example.propconnectsolo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        name = copy.name;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private int subscription;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Property> property;

    public User(long id){ this.id = id;}

    public User() {
    }

    public User(long id, String name, String username, String email, String password, int subscription, List<Property> property) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
        this.property = property;
    }

    public User(String name, String username, String email, String password, int subscription, List<Property> property) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
        this.property = property;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Property> getProperty() {
        return property;
    }

    public void setProperty(List<Property> property) {
        this.property = property;
    }

    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(int subscription) {
        this.subscription = subscription;
    }
}
