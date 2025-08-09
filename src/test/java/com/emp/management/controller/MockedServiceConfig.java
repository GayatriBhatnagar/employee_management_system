package com.emp.management.controller;

import com.emp.management.service.EmployeeService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;



@TestConfiguration
public class MockedServiceConfig {

    @Bean
    public EmployeeService employeeService(){
        return Mockito.mock(EmployeeService.class);
    }
}












