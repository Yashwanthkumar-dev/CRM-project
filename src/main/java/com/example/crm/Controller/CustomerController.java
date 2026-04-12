package com.example.crm.Controller;

import com.example.crm.Model.CustomerEntity;
import com.example.crm.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //testing controller
    @GetMapping
    public String welcome() {
        return "welcome to crm project";
    }

    // adding customer
    @PostMapping("/addCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerEntity customer) {
        return customerService.createCustomerService(customer);
    }

    //view all customer
    @GetMapping("/viewAllCustomer")
    public ResponseEntity<?> viewAllCustomerDetails() {
        return customerService.viewAllCustomerDetails();
    }

    //update customer all details by using name
    @PutMapping("/updateSingleCustomerAllDetails/{id}")
    public ResponseEntity<?> updateCustomerAllDetails(@PathVariable Integer id , @RequestBody CustomerEntity customer) {
        return customerService.updateCustomerAllDetails(id,customer);
    }

    //    delete all Customer
    @DeleteMapping("deleteAllCustomer")
    public ResponseEntity<?> deleteAllCustomer() {
        return customerService.deleteAllCustomer();
    }

    //    delete single customer by using id
    @DeleteMapping("/deleteSingleCustomerById/{id}")
    public ResponseEntity<?> deleteSingleCustomerById(@PathVariable Integer id) {
        return customerService.deleteSingleCustomerById(id);
    }

    //search customer details by using name
    @GetMapping("/searchCustomerDetails")
    public ResponseEntity<?> searchCustomerDetails(@RequestParam String name) {
        return customerService.searchCustomerDetails(name);
    }

    @GetMapping("/customer-csv-report")
    public ResponseEntity<?>getAllCustomerCsvReport(){
        return customerService.getAllCustomerCsvReport();
    }
}
