package com.sebasira.MediumTutorialSecurity.model;

import javax.persistence.*;

/**
 * Created by sebas on 16/07/17.
 */
@Entity
@Table(name = "role")
public class Role {

    /* ID */
    /* ** */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /* ROLE */
    /* **** */
    @Column(name="role")
    private String role;
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
