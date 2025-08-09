package com.emp.management.controller;

import com.emp.management.dto.DepartmentRequest;
import com.emp.management.dto.DepartmentResponse;
import com.emp.management.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<DepartmentResponse> postDepartment(@RequestBody DepartmentRequest departmentRequest){
             DepartmentResponse response= departmentService.createDepartment(departmentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
