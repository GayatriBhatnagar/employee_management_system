package com.emp.management.mapper;

import com.emp.management.dto.EmployeeOldestTenureResponse;
import com.emp.management.dto.EmployeeRequest;
import com.emp.management.dto.EmployeeResponse;
import com.emp.management.entity.Department;
import com.emp.management.entity.Employee;
import com.emp.management.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring")
public interface EmployeeMapper {

    @Mapping(source = "department.departmentId", target = "departmentId")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "employeeId", target = "employeeId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "hireDate", target = "hireDate")
    @Mapping(source = "job.jobId", target = "jobId")
    @Mapping(source = "manager.employeeId", target = "managerId")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "salary", target = "salary")
    EmployeeResponse toEmployeeResponse(Employee employee);


    @Mapping(source = "dto.email", target = "email")
    @Mapping(source = "dto.firstName", target = "firstName")
    @Mapping(source = "dto.lastName", target = "lastName")
    @Mapping(source = "dto.employeeId", target ="employeeId")
    @Mapping(source = "dto.phoneNumber", target = "phoneNumber")
    @Mapping(source = "dto.hireDate", target = "hireDate")
    @Mapping(source = "dto.salary", target = "salary")
    @Mapping(source = "job", target = "job")
    @Mapping(source = "manager", target = "manager")
    @Mapping(source = "department", target = "department")
    Employee toEmployee(EmployeeRequest dto, Job job, Employee manager, Department department);




    EmployeeOldestTenureResponse toOldestTenureResponse(Employee employee);
}