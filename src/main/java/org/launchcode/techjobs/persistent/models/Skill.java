package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
//contains no arg constructor
public class Skill extends AbstractEntity {
    @NotBlank(message = "Description is required")
    @Size(max = 100, message = "Description must be less than 100 characters")
    private String description;

//    Part 4.1: Add jobs field, mapped to skills. Many-to-many relationship
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    public Skill(String description) {
        this.description = description;
    }

//    Part 2.4 No arg. constructor bc @Entity.
    public Skill () {}

//    getter and setters for public access
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    Part 4.1 - ManyToMany - Refactor Skill.jobs - Only added getter for new jobs field, since skills does not need to
//    modify the jobs list.
    public List<Job> getJobs() {
        return jobs;
    }
}
