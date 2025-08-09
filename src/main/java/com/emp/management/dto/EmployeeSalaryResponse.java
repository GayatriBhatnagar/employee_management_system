package com.emp.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryResponse {

    private Integer employeeId;
    private  String firstName;
    private String lastName;
    private BigDecimal salary;
}
