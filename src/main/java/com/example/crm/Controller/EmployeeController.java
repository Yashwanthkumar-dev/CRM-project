package com.example.crm.Controller;

import com.example.crm.Model.EmployeeEntity;
import com.example.crm.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final EmployeeService employeeService;

    // --- create employee ---
    @PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.createEmployee(employee);
    }

//    --- fetch employees ---
    @GetMapping("/fetchEmployee")
    public ResponseEntity<?> fetchEmployee(){
        return employeeService.fetchEmployee();
    }
}
