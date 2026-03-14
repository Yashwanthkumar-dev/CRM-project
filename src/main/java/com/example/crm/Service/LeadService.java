package com.example.crm.Service;

import com.example.crm.Model.CustomerEntity;
import com.example.crm.Model.LeadEntity;
import com.example.crm.Repository.CustomerRepository;
import com.example.crm.Repository.LeadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    @Autowired
    private LeadRepository leadRepo;
    @Autowired
    private CustomerRepository customerRepo;

    public ResponseEntity<?> createNewLead(LeadEntity lead) {
        try {
            if (lead.getId() != null) {
                Optional<LeadEntity> isLead = leadRepo.findById(lead.getId());
                if (isLead.isPresent()) {
                    isLead.get().setFollowUps("updated lead");
                    leadRepo.save(isLead.get());
                }
            }
            if (lead.getFollowUps() == null) {
                lead.setFollowUps("new lead");
            }
            leadRepo.save(lead);
            return ResponseEntity.status(HttpStatus.CREATED).body("new lead was created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> viewLeads() {
        try {
            List<LeadEntity> allLeads = leadRepo.findAll();
            if (allLeads.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("leads are not created");
            }
            return ResponseEntity.status(HttpStatus.FOUND).body(allLeads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> updateLeadStatus(String status, Integer id) {
        try {
            Optional<LeadEntity> isLead = leadRepo.findById(id);
            if (isLead.isPresent()) {
                LeadEntity lead = isLead.get();
                lead.setFollowUps(status);
                leadRepo.save(lead);
                return ResponseEntity.status(HttpStatus.OK).body("lead was updated");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("lead was not found in this name");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @Transactional
    public ResponseEntity<?> convertLeadToCustomer(Integer id) {
        try {
            Optional<LeadEntity> isLead = leadRepo.findById(id);
            if (isLead.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("lead was not found in this name " + isLead.get().getName());
            }
            LeadEntity lead = isLead.get();
            if (lead.getFollowUps().equalsIgnoreCase("intrested")) {
                CustomerEntity newCustomer = new CustomerEntity();
                newCustomer.setName(lead.getName());
                newCustomer.setEmail(lead.getEmail());
                newCustomer.setPhoneNumber(lead.getPhone());
                newCustomer.setLocation(lead.getLocation());
                newCustomer.setCompany(lead.getCompany());
                customerRepo.save(newCustomer);
                leadRepo.delete(lead);
                return ResponseEntity.status(HttpStatus.CREATED).body("converted lead to customer , customer name is " + lead.getName());
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(" lead was not intrested ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteAllLeads() {
        try {
            leadRepo.deleteAll();
            return ResponseEntity.status(
                    HttpStatus.OK
            ).body("all leads deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
}
