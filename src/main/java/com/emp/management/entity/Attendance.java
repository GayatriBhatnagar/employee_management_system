package com.emp.management.entity;

import com.emp.management.util.EnumAttendanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="attendance")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="attendance_id")
    private Integer attendanceId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name="check_in")
    private LocalTime checkIn;

    @Column(name="check_out")
    private LocalTime checkOut;

    @Enumerated(EnumType.STRING)
    @Column(name= "status")
    private EnumAttendanceStatus status;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    private Employee employee;


}
