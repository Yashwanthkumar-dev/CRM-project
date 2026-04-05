package com.example.crm.SecurityService;

import com.example.crm.Model.EmployeeEntity;
import com.example.crm.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeEntity employee = employeeRepo.findByemail(email).orElseThrow(() -> new UsernameNotFoundException("user email was not found"));
        return new User(employee.getEmail(), employee.getPassword(), List.of(new SimpleGrantedAuthority(employee.getRole())));
    }
}
