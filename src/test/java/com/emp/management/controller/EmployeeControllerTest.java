package com.emp.management.controller;

import com.emp.management.dto.EmployeeRequest;
import com.emp.management.dto.EmployeeResponse;
import com.emp.management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(EmployeeController.class)
@Import(MockedServiceConfig.class)
public class EmployeeControllerTest {


  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private EmployeeService employeeService;

  private EmployeeRequest employeeRequest;
  private EmployeeResponse employeeResponse;

    @BeforeEach
  void setUp(){
      employeeRequest = EmployeeRequest.builder().employeeId(1L).
              JobId(2L).firstName("Alice").lastName("Smith").hireDate(LocalDate.now())
              .managerId(3L).salary(BigDecimal.valueOf(23432))
              .departmentId(4L).email("sgs@gmail.com").build();

      employeeResponse = EmployeeResponse.builder().employeeId(1L).
              jobId(2L).firstName("Alice").lastName("Smith").hireDate(LocalDate.now())
              .managerId(3L).salary(BigDecimal.valueOf(23432))
              .departmentId(4L).email("sgs@gmail.com").build();

  }

  @Test
    public void createEmployee_ValidData_ReturnsCreateEmployee() throws Exception {
      when(employeeService.createEmployee(any(EmployeeRequest.class)))
              .thenReturn(employeeResponse);
      mockMvc.perform(post("/api/employees")
                      .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(employeeRequest)))
              .andExpect(status().isCreated())
              .andExpect(jsonPath("$.firstName").value("Alice"));

  }


  @Test
  public void getEmployees_ReturnsAllEmployees() throws Exception {
    Pageable pageable = PageRequest.of(1, 10);
Page<EmployeeResponse> page= new PageImpl<>(List.of(employeeResponse), pageable,1);
    when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(page);
    mockMvc.perform(get("/api/getallemployees"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size").value(10)).
            andExpect(jsonPath("$.content[0].employeeId").value(1));
  }



}
