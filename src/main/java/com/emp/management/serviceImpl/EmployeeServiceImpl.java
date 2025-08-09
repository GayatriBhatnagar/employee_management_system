package com.emp.management.serviceImpl;


import com.emp.management.dto.EmployeeRequest;
import com.emp.management.dto.EmployeeResponse;
import com.emp.management.entity.Department;
import com.emp.management.entity.Employee;
import com.emp.management.entity.Job;
import com.emp.management.exception.EmployeeNotFoundException;
import com.emp.management.mapper.EmployeeMapper;
import com.emp.management.repository.DepartmentRepo;
import com.emp.management.repository.EmployeeRepo;
import com.emp.management.repository.JobRepo;
import com.emp.management.repository.SalaryRepo;
import com.emp.management.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//security + logging + validations + pagination
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final JobRepo jobRepo;
    private final DepartmentRepo departmentRepo;
    private final SalaryRepo salaryRepo;
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, JobRepo jobRepo, DepartmentRepo departmentRepo, SalaryRepo salaryRepo) {
        this.employeeRepo = employeeRepo;
        this.jobRepo = jobRepo;
        this.departmentRepo = departmentRepo;
        this.salaryRepo = salaryRepo;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeDTO) {
        Department department = null;
        //if dept id is not null but not found throw runtime
        if (employeeDTO.getDepartmentId() != null) {
            department = departmentRepo.findById(Math.toIntExact
                            (employeeDTO.getDepartmentId()))
                    .orElseThrow(() -> new EmployeeNotFoundException("Department not found with id: " + employeeDTO.getDepartmentId()));
        }
// if job id is null thrwo illegal arugument exception
        Long jobId = employeeDTO.getJobId();
        if (jobId == null) {
            throw new IllegalArgumentException("JobId cannot be Null");
        }
        //if jobid not fount throw runtime exception
        Integer jobIdInInt = Math.toIntExact(jobId);
        Job job = jobRepo.findById(jobIdInInt).orElseThrow(() -> new RuntimeException("Job not found with Id: " + employeeDTO.getJobId()));

        Employee manager = null;
        //if manager is given but not found throw runtime exception
        if (employeeDTO.getManagerId() != null) {
            manager = employeeRepo.findById(employeeDTO.getManagerId().intValue())
                    .orElseThrow(() -> new RuntimeException("Manager Id with : " + employeeDTO.getManagerId() + "does not exist"));
        }
        Employee result = employeeDTOReqToEntity(employeeDTO, job, manager, department);
        //save
        Employee savedEmp = employeeRepo.save(result);
        Long managerId = savedEmp.getManager() != null
                ? Long.valueOf(savedEmp.getManager().getEmployeeId())
                : null;
        return employeeMapper.toEmployeeResponse(savedEmp);
    }


    @Override
    public Page<EmployeeResponse> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepo.findAll(pageable);
        return employees.map(employeeMapper::toEmployeeResponse);

    }

    @Override
    public EmployeeResponse getEmployeeById(Integer id) {
      return employeeRepo.findById(id).map(employeeMapper::toEmployeeResponse).orElseThrow(
              () -> new EmployeeNotFoundException("Employee not found")
      );
    }

    private Employee employeeDTOReqToEntity(EmployeeRequest employeeDTO, Job job, Employee manager, Department department) {

        Employee.EmployeeBuilder builder = Employee.builder().firstName(employeeDTO.getFirstName()).lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail()).phoneNumber(employeeDTO.getPhoneNumber()).
                hireDate(employeeDTO.getHireDate()).job(job).salary(employeeDTO.getSalary()).
                manager(manager).
                department(department);
        if (employeeDTO.getEmployeeId() != null) {
            builder.employeeId(Math.toIntExact(employeeDTO.getEmployeeId()));
        }

        return builder.build();

    }


}
