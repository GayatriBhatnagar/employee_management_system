package com.emp.management.serviceImpl;

import com.emp.management.dto.EmployeeRequest;
import com.emp.management.dto.EmployeeResponse;
import com.emp.management.entity.Department;
import com.emp.management.entity.Employee;
import com.emp.management.entity.Job;
import com.emp.management.entity.Salary;
import com.emp.management.repository.DepartmentRepo;
import com.emp.management.repository.EmployeeRepo;
import com.emp.management.repository.JobRepo;
import com.emp.management.repository.SalaryRepo;
import com.emp.management.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private JobRepo jobRepo;

    @Mock
    private DepartmentRepo departmentRepo;

    @Mock
    private SalaryRepo salaryRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private EmployeeRequest request, requestWithJobIdNull, requestWithDeptIdThatNotExist;
    private Job job;
    private Department department;
    private Employee manager;
    private Employee employeeSaved;
    private List<Employee> employeeList;


    @BeforeEach
    void setUp(){
        request=  EmployeeRequest.builder()
                .employeeId(2L).firstName("Alice").
                lastName("Smith").email("Alicesmith@gmail.com").departmentId(2L).salary(BigDecimal.valueOf(234)).
                hireDate(LocalDate.now()).managerId(2L).phoneNumber("234343").JobId(8L).build();

        requestWithJobIdNull=  EmployeeRequest.builder()
                .employeeId(2L).firstName("Alice").
                lastName("Smith").email("Alicesmith@gmail.com").departmentId(2L).
                hireDate(LocalDate.now()).managerId(2L).phoneNumber("234343").JobId(null).build();

        requestWithDeptIdThatNotExist= EmployeeRequest.builder()
                .employeeId(2L).firstName("Alice").
                lastName("Smith").email("Alicesmith@gmail.com").departmentId(8L).
                hireDate(LocalDate.now()).managerId(2L).phoneNumber("234343").JobId(null).build();

        job = Job.builder().jobId(8).build();
        department= Department.builder().departmentId(2).build();
        manager = Employee.builder().employeeId(1).build();
        employeeSaved=  Employee.builder()
                .employeeId(2).firstName("Alice").
                lastName("Smith").email("Alicesmith@gmail.com").department(department).
                hireDate(LocalDate.now()).manager(manager).phoneNumber("234343").job(job).build();

        employeeList = new ArrayList<>();
        employeeList.add(employeeSaved);

    }

    @Test
    public void createEmployee_withValidData_ReturnsCreateEmployee(){
        when(departmentRepo.findById(2)).thenReturn(Optional.of(department));
        when(jobRepo.findById(8)).thenReturn(Optional.of(job));
        when(employeeRepo.findById(2)).thenReturn(Optional.of(manager));
        when(employeeRepo.save(any(Employee.class))).thenReturn(employeeSaved);
        EmployeeResponse response = employeeServiceImpl.createEmployee(request);

        //asserts
        assertNotNull(response);
        assertEquals("Smith", response.getLastName());
        assertEquals("Alice", response.getFirstName());

        //verify
        verify(jobRepo).findById(8);

    }

    @Test
    public void createEmployee_WithJobIdNull_ThrowIllegalArgumentException(){
        when(departmentRepo.findById(2)).thenReturn(Optional.of(department));
        IllegalArgumentException exception= assertThrows(IllegalArgumentException.class, ()-> employeeServiceImpl.createEmployee(requestWithJobIdNull));
       assertEquals("JobId cannot be Null", exception.getMessage());
    }

    @Test
    public void createEmployee_WithDepartmentIdNotExist_ThrowRunTimeException(){
        RuntimeException exception= assertThrows(RuntimeException.class, ()->employeeServiceImpl.createEmployee(requestWithDeptIdThatNotExist));
      assertEquals("Department not found with id: " + requestWithDeptIdThatNotExist.getDepartmentId(), exception.getMessage());

    }

    @Test
    public void  getAllEmployees_ReturnsAllEmployees(){
        when(employeeRepo.findAll()).thenReturn(employeeList);
        assertEquals("Alicesmith@gmail.com" , employeeList.get(0).getEmail());
    }

    @Test
    public void getEmployeeById_ReturnsValidUserwithId() {
        EmployeeResponse response = EmployeeResponse.builder().employeeId(1L).firstName("m").build();

        when(employeeServiceImpl.getEmployeeById(Math.toIntExact(request.getEmployeeId()))).thenReturn(response);
    }
}
