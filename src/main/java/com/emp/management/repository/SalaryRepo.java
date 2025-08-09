package com.emp.management.repository;

import com.emp.management.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepo extends JpaRepository<Salary, Integer> {
}
