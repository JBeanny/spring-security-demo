package com.example.spring_security_demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    private String id;
    private String name;
    private String skill;
    private String attribute;
    private String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pokemon() {
    }

    public Pokemon(String id, String name, String skill, String attribute, String description) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.attribute = attribute;
        this.description = description;
    }
}
