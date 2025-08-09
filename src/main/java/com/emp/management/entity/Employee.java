package com.emp.management.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="employees")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Employee {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id",referencedColumnName = "department_id", nullable = false)
    private Department department;

    @NotNull
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Integer employeeId;

    @Column(name="first_name")
    private String firstName;


    @Column(name="last_name")
    private String lastName;

    @Column(name="hire_date")
    private LocalDate hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="job_id",referencedColumnName = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private Employee manager;

    @Column(name="phone_number")
    private String phoneNumber;

    private BigDecimal salary;

    @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee" , cascade = CascadeType.ALL)
    private List<Attendance> attendance;


}
