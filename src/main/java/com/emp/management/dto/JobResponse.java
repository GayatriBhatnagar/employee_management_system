package com.emp.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
    private int jobId;
    private String title;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}