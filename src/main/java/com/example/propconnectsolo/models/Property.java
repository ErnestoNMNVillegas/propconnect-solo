package com.example.propconnectsolo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 75)
    private String street_add;

    @Column(nullable = false, length = 25)
    private String city;

    @Column(nullable = false, length = 50)
    private String state;

    @Column(nullable = false, length = 100)
    private int zip;

    @Column(nullable = false, length = 100)
    private String category;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Property(){
    }

    public Property(long id, String street_add, String city, String state, int zip, String category, List<Note> notes, User user) {
        this.id = id;
        this.street_add = street_add;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.category = category;
        this.notes = notes;
        this.user = user;
    }

    public Property(String street_add, String city, String state, int zip, String category, List<Note> notes, User user) {
        this.street_add = street_add;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.category = category;
        this.notes = notes;
        this.user = user;
    }

    public Property(long id) { this.id = id; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet_add() {
        return street_add;
    }

    public void setStreet_add(String street_add) {
        this.street_add = street_add;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
