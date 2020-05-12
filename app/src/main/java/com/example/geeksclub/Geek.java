package com.example.geeksclub;

public class Geek {
    private String id,name,date,hostname,description,college,website,goodies,contact,price;

    public Geek(String id, String name, String date, String hostname, String description, String college, String website, String goodies, String contact, String price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.hostname = hostname;
        this.description = description;
        this.college = college;
        this.website = website;
        this.goodies = goodies;
        this.contact = contact;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String webite) {
        this.website = webite;
    }

    public String getGoodies() {
        return goodies;
    }

    public void setGoodies(String goodies) {
        this.goodies = goodies;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
