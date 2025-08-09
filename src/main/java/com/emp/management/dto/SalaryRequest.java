package com.emp.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalaryRequest {

    private Integer salaryId;
    private Integer employeeId;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
}
