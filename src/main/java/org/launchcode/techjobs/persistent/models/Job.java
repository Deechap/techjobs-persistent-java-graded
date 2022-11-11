package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

//    these are redundant due to the AbstractEntity
//    @Id
//    @GeneratedValue
//    private int id;
//
//    private String name;

    @ManyToOne
//    @JoinColumn(name = "employer_id")
    private Employer employer;
    private List<Skill> skills = new ArrayList<>();

    public Job() {
    }

    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.
//Part 3.2.1 Updated Job Model class so id and name getter/setter no longer needed due to AbstractEntitiy
//    public String getName() {
//        return name;
//    }

    //    public void setName(String name) {
//        this.name = name;
//    }
//
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    //    public String getSkills() {
//        return skills;
//    }
//
//    public void setSkills(String skills) {
//        this.skills = skills;
//    }
//}
//    Part 4.2 Set up Many-to-Many and refactor job.skills, getter/setters types to List<Skill> to match above
    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}