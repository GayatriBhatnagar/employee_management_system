package com.emp.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="salaries")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="salary_id")
    private Integer salaryId;

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}
