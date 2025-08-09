package com.emp.management.service;

import com.emp.management.dto.DepartmentRequest;
import com.emp.management.dto.DepartmentResponse;
import com.emp.management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentService  {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
}
