package com.emp.management.controller;

import com.emp.management.entity.Employee;
import com.emp.management.entity.Job;
import com.emp.management.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }

    @PostMapping("/job")
    public ResponseEntity<Job> postJob(@RequestBody Job jobRequestdto){
        Job saved= jobService.postEmployee(jobRequestdto);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }
}
