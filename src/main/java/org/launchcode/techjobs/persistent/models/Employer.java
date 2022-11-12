package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

//    checks for location input and length
    @NotBlank(message = "Location required")
    @Size(min= 1, max = 100, message = "Please limit location to 100 characters.")
    private String location;

//    Part 3.1 Adding One-to-Many (Add jobs field to Employer to join the column with a foreign key employer_id)
//    one employer to many jobs
    @OneToMany
//    this will make a column for the foreign key to Job table. This will connect to Employer class primary key.
    @JoinColumn(name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();

//adds public access to employer
    public Employer () {

    }
//gets/sets location from user
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
