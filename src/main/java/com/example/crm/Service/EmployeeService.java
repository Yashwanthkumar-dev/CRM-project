package com.example.crm.Service;

import com.example.crm.Model.EmployeeEntity;
import com.example.crm.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepos;

    //     --- create employee ---
    public ResponseEntity<?> createEmployee(EmployeeEntity employee) {
        try {
            Optional<EmployeeEntity> isEmployee = employeeRepos.findByemail(employee.getEmail());
            if (isEmployee.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("employee was already exists with the email :" + employee.getEmail());
            }
            employeeRepos.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body("employee created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    // --- fetch employee ---
    public ResponseEntity<?> fetchEmployee() {
        try {
            List<EmployeeEntity> allEmployee = employeeRepos.findAll();
            if (allEmployee.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee was empty");
            }
            return ResponseEntity.status(HttpStatus.OK).body(allEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
