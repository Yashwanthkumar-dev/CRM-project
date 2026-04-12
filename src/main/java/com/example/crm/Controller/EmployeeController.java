package com.example.crm.Controller;

import com.example.crm.Model.EmployeeEntity;
import com.example.crm.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    // --- create employee ---
    @PostMapping("/createEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.createEmployee(employee);
    }

    //    --- fetch employees ---
    @GetMapping("/fetchEmployee")
    public ResponseEntity<?> fetchEmployee() {
        return employeeService.fetchEmployee();
    }

    //  --- fetch employee profile ---
    @GetMapping("/fetchEmployeeProfile")
    public ResponseEntity<?> fetchEmployeeProfile() {
        return employeeService.fetchEmployeeProfile();
    }

    //     --- upload employee image ---
    @PostMapping("/uploadEmployeeImage")
    public ResponseEntity<?> uploadEmployeeImage(@RequestParam MultipartFile image) {
        return employeeService.uploadEmployeeProfile(image);
    }

//     --- delete employee ---
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteEmployeeById/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Integer id){
        return employeeService.deleteEmployeeById(id);
    }

    @PatchMapping("updateEmployeeToAdmin/{id}")
    public ResponseEntity<?> updateEmployeeToAdmin(@RequestParam String role, @PathVariable Integer id){
        return employeeService.updateEmployeeToAdmin(role , id);
    }

}
