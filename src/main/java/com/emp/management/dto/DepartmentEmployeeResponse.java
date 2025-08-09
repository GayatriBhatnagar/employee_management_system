package com.emp.management.dto;

import com.emp.management.entity.Department;
import com.emp.management.entity.Employee;
import com.emp.management.entity.Salary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEmployeeResponse {

    private Integer DepartmentId;
    private String DepartmentName;
    private List<EmployeeSalaryResponse> employeeSalaryResponseList;

}
