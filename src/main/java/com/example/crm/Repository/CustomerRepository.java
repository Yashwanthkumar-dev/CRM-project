package com.example.crm.Repository;

import com.example.crm.Model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    Optional<CustomerEntity>findByname(String name);
    List<CustomerEntity> findByNameContainingIgnoreCase(String name);


}
