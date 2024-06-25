package com.furkanreyhan.SimpleLogin.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "projectName")
    String projectName;
    @ManyToMany
    @JoinTable(
            name = "project_user",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> userList;

    public Project() {
    }

    public Project(Long id, String projectName, List<User> userList) {
        this.id = id;
        this.projectName = projectName;
        this.userList = userList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

