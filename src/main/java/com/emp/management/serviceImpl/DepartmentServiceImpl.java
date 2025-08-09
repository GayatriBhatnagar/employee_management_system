package com.emp.management.serviceImpl;

import com.emp.management.dto.DepartmentRequest;
import com.emp.management.dto.DepartmentResponse;
import com.emp.management.entity.Department;
import com.emp.management.repository.DepartmentRepo;
import com.emp.management.service.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo){
        this.departmentRepo= departmentRepo;
    }


    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department result= reqtoDept(departmentRequest);
       Department savedDept = departmentRepo.save(result);
        return entitytoDepartmentResponse(savedDept);
    }

    private DepartmentResponse entitytoDepartmentResponse(Department savedDept) {
        return DepartmentResponse.builder().
                departmentId(savedDept.getDepartmentId()).name(savedDept.getName()).
                location(savedDept.getLocation()).build();
    }

    private Department reqtoDept(DepartmentRequest departmentRequest) {
        return Department.builder().
                departmentId(departmentRequest.getDepartmentId()).name(departmentRequest.getName()).
                location(departmentRequest.getLocation()).build();
    }
}
