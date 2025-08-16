package com.emp.management.controller;


import com.emp.management.dto.EmployeeOldestTenureResponse;
import com.emp.management.dto.EmployeeRequest;
import com.emp.management.dto.EmployeeResponse;
import com.emp.management.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

  @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> postEmployees(@RequestBody EmployeeRequest employeeDTO) {
        EmployeeResponse response = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/getallemployees")
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            @RequestParam(defaultValue = "0")int pageNum,
            @RequestParam(defaultValue = "10")int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        Page<EmployeeResponse> responses= employeeService.getAllEmployees(pageable);
      return  responses.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(responses);

    }


    @GetMapping("/getemployeeby/{id}")
    public ResponseEntity<EmployeeResponse> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

   /* @GetMapping("/getemployeeby{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Integer id){
    return employeeService.getEmployeeById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }*/

    @GetMapping("/getemployeestop5")
    public ResponseEntity<List<EmployeeOldestTenureResponse>> getTopFiveOldestEmp(){
        List<EmployeeOldestTenureResponse> response = employeeService.getTopFiveOldestEmp();
        return ResponseEntity.ok(response);

    }


}
