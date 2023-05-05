package com.example.propconnectsolo.models;

import jakarta.persistence.*;

@Entity
@Table(name="notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false, length = 50)
    private String category;

    @Column
    private String image_url;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;


    public Note(long id, String title, String body, String category, String image_url, String date, Property property) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.category = category;
        this.image_url = image_url;
        this.date = date;
        this.property = property;
    }

    public Note(String title, String body, String category, String image_url, String date, Property property) {
        this.title = title;
        this.body = body;
        this.category = category;
        this.image_url = image_url;
        this.date = date;
        this.property = property;
    }

    public Note(){}

    public Note(long id){ this.id = id; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
