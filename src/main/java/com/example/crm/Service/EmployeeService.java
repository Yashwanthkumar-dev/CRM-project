package com.example.crm.Service;

import com.example.crm.Model.EmployeeEntity;
import com.example.crm.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseEntity<?> fetchEmployeeProfile() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUserEmail = auth.getName();

            Optional<EmployeeEntity> employee = employeeRepos.findByemail(currentUserEmail);

            if (employee.isPresent()) {
                return ResponseEntity.ok(employee.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    public ResponseEntity<?> uploadEmployeeProfile(MultipartFile image) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentEmail = auth.getName();

            Optional<EmployeeEntity> isEmployee = employeeRepos.findByemail(currentEmail);
            if (isEmployee.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employe was not found");
            }
            if (image == null || image.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("image file was empty");
            }
            EmployeeEntity employee = isEmployee.get();
            employee.setImageName(image.getOriginalFilename());
            employee.setImageType(image.getContentType());
            employee.setImageData(image.getBytes());
            employeeRepos.save(employee);
            return ResponseEntity.ok("image  uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    public ResponseEntity<?> deleteEmployeeById(Integer id) {
        try {
            Optional<EmployeeEntity> isemployee = employeeRepos.findById(id);
            if (isemployee.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee was not found in this id :" + id);
            }

            EmployeeEntity employee = isemployee.get();
            employeeRepos.delete(employee);
            return ResponseEntity.status(HttpStatus.OK).body("employee deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    public ResponseEntity<?> updateEmployeeToAdmin(String role, Integer id) {
        try {
            Optional<EmployeeEntity> isEmployee = employeeRepos.findById(id);
            if(isEmployee.isPresent()){
              EmployeeEntity employee= isEmployee.get();
              employee.setRole(role);
              employeeRepos.save(employee);
              return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee was not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
}
