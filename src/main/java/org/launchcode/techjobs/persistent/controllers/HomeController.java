package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    /* Part 3.3 Updating HomeController: Add employerRepository field that is AutoWired - looks for an EmployerRepository object
        to manage and create an instance of it to populate the employerRepository field variable with the CRUD data from our ORM. */
    @Autowired
    private EmployerRepository employerRepository;

//    4.3-updates HomeController matched to repository
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
//    these models link to the repositories
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
//        3.3.2 Update the HomeController: check the template/add.html to get "employers" attribute. This will add Employer data from employerRepository to the model
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
//        Part 3.3.4:  check if an employerId value is present/submitted and
//        then save to the jobRepository. NOTE: Optional provides a temporary container to hold the data by ID.
//        checks to see if a value is actually present and sets those values to attach to the newJob. Googling and looking at coding_events
        Optional<Employer> optionalEmployer = employerRepository.findById(employerId);
        if (optionalEmployer.isPresent()) {
            newJob.setEmployer(optionalEmployer.get());
        }

//        4.3 update HomeController
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);

        jobRepository.save(newJob);
        model.addAttribute("job", jobRepository.findAll());

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional <Job> result = jobRepository.findById(jobId);

        if (result.isPresent()) {
            Job job = result.get();
            model.addAttribute("job", job);
            return "view";
        } else {
            return "redirect:../";
        }
    }
    }