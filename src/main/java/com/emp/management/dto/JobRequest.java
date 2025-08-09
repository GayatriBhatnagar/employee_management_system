package com.emp.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {

    private int jobId;
    private String title;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
