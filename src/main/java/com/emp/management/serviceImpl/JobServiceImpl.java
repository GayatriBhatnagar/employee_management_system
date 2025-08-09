package com.emp.management.serviceImpl;

import com.emp.management.entity.Job;
import com.emp.management.repository.JobRepo;
import com.emp.management.service.JobService;
import org.springframework.stereotype.Service;


@Service
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;

    public JobServiceImpl(JobRepo jobRepo){
        this.jobRepo=jobRepo;
    }
    @Override
    public Job postEmployee(Job jobRequest) {
        return jobRepo.save(jobRequest);
    }
}
