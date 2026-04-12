package com.example.crm.Controller;

import com.example.crm.DTO.AuthControllerDTO;
import com.example.crm.JWT.Util;
import com.example.crm.Model.EmployeeEntity;
import com.example.crm.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    private final EmployeeRepository employeeRepo;
    private final Util util;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> Registration(@RequestBody AuthControllerDTO dto) {
        String email = dto.getEmail();
        if (employeeRepo.findByemail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("employee was already registered");
        }
        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setEmail(dto.getEmail());
        newEmployee.setName(dto.getName());
        newEmployee.setNumber(dto.getPhone());
        newEmployee.setPassword(passwordEncoder.encode(dto.getPassword()));
        newEmployee.setLocation(dto.getLocation());
        newEmployee.setRole("EMPLOYEE");
        employeeRepo.save(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody AuthControllerDTO dto) {
        Optional<EmployeeEntity> employee = employeeRepo.findByemail(dto.getEmail());
        if (employee.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email was not found pls register the mail");
        }
        EmployeeEntity emp = employee.get();
        if (passwordEncoder.matches(dto.getPassword(), emp.getPassword())) {
            String role = (emp.getRole()!= null ? emp.getRole():"EMPLOYEE");
            String token = util.generateToken(emp.getEmail(), "ROLE_" + role);
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", role);
            response.put("location", emp.getLocation());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
    }
}
