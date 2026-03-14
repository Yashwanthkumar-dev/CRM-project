package com.example.crm.Service;

import com.example.crm.Model.CustomerEntity;
import com.example.crm.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepo;

    public ResponseEntity<?> createCustomerService(CustomerEntity customer) {
        try {
            Optional<CustomerEntity> isCustomer = customerRepo.findByname(customer.getName());
            if (isCustomer.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer was already existed");
            }
            customerRepo.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("customer was successfully created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> viewAllCustomerDetails() {
        try {
            List<CustomerEntity> allCustomer = customerRepo.findAll();
            if (allCustomer.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer was found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(allCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    public ResponseEntity<?> updateCustomerAllDetails(String oldName, String newName, String email, String phoneNumber, String company, String location) {
        try {
            Optional<CustomerEntity> existingCustomer = customerRepo.findByname(oldName);
            if (existingCustomer.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("customer was not found in this name " + oldName);
            }
            CustomerEntity updateCustomer = existingCustomer.get();
            updateCustomer.setName(newName);
            updateCustomer.setEmail(email);
            updateCustomer.setPhoneNumber(phoneNumber);
            updateCustomer.setCompany(company);
            updateCustomer.setLocation(location);
            customerRepo.save(updateCustomer);
            return ResponseEntity.status(HttpStatus.OK).body("Customer details was updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    public ResponseEntity<?> deleteAllCustomer() {
        try {
            customerRepo.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).body("deleted All customers");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteSingleCustomerById(Integer id) {
        try {
            Optional<CustomerEntity> isCustomerIsThere = customerRepo.findById(id);
            if (isCustomerIsThere.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer was not found");
            }
            customerRepo.delete(isCustomerIsThere.get());
            return ResponseEntity.status(HttpStatus.OK).body("Customer was deleted sucessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> searchCustomerDetails(String name) {
        try {
            Optional<CustomerEntity> isCustomerAvailable = customerRepo.findByname(name);
            if (isCustomerAvailable.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer was not found in this name " + name);
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(isCustomerAvailable);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
