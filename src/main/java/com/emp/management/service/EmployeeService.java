package com.emp.management.service;


import com.emp.management.dto.EmployeeOldestTenureResponse;
import com.emp.management.dto.EmployeeRequest;
import com.emp.management.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest employeeDTO);
/*    Page<EmployeeResponse> getAllEmployees(Pageable pageable);*/
    EmployeeResponse getEmployeeById(Integer id);

    Page<EmployeeResponse> getAllEmployees(Pageable pageable);

    List<EmployeeOldestTenureResponse> getTopFiveOldestEmp();
}
