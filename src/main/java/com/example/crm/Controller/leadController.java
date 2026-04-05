package com.example.crm.Controller;

import com.example.crm.Model.LeadEntity;
import com.example.crm.Service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/lead")
@CrossOrigin(origins = "http://localhost:5173")
public class leadController {
    @Autowired
    private LeadService leadService;

    @PostMapping("/addLead")
    public ResponseEntity<?> createNewLead(@RequestBody LeadEntity lead) {
        return leadService.createNewLead(lead);
    }

    @GetMapping("/viewLeads")
    public ResponseEntity<?> viewLeads() {
        return leadService.viewLeads();
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<?> updateLeadStatus(@RequestParam String status, @PathVariable Integer id) {
        return leadService.updateLeadStatus(status, id);
    }

    @PostMapping("/convert/{id}")
    public ResponseEntity<?> convertLeadToCustomer(@PathVariable Integer id) {
        return leadService.convertLeadToCustomer(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLeadById(@PathVariable Integer id) {
        return leadService.viewLeadById(id);
    }

    @DeleteMapping("/deleteAllLeads")
    public ResponseEntity<?> deleteAllLeads() {
        return leadService.deleteAllLeads();
    }

    @DeleteMapping("/deleteSingleLead/{id}")
    public ResponseEntity<?> deleteSingleLead(@PathVariable Integer id) {
        return leadService.deleteSingleLead(id);
    }

    @PatchMapping("/updateNextTime/{id}")
    public ResponseEntity<?> updateNextFollowTime(@RequestParam String time , @PathVariable Integer id){
        return leadService.updateNextFollowTime(time , id);
    }
    @GetMapping("/leadsAnalytics")
    public ResponseEntity<?> leadsAnalytics(){
        return leadService.leadAnalytics();
    }
    @GetMapping("/leadSource")
        public ResponseEntity<?> leadSourceData(){
            return leadService.leadSourceData();
        }

}
