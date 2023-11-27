package com.sandbox.sportsplayers.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "level")
    private int level;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToMany(mappedBy = "players")
    private Set<Sport> sports;

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public enum Gender {
        male, female
    }
}
