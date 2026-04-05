package com.example.crm.Repository;

import com.example.crm.Model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByemail(String email);
}
