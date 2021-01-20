package com.bezkoder.spring.files.upload.db.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import java.nio.file.Path;

@Entity
@Table(name = "profilepics")
public class ProfilePicz {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;

    private int userId;

    private String path;
    public ProfilePicz() {
    }
    public ProfilePicz(String name, String path, int userId) {
        this.name = name;
        this.path = path;
        this.userId = userId;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public void setPath(String path) {
        this.path = path;
    }
}
