package com.example.crm.Service;

import com.example.crm.DTO.DashboardDto;
import com.example.crm.Model.LeadEntity;
import com.example.crm.Repository.CustomerRepository;
import com.example.crm.Repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class dashboardService {
    @Autowired
    private  LeadRepository leadRepo;
    @Autowired
    private  CustomerRepository customerRepo;

    public  ResponseEntity<?> getDashboardDetails() {
        try {
            Long totalLead = leadRepo.count();
            Long totalCustomer = customerRepo.count();

            Long totalInterstedCustomer = leadRepo.countByFollowUps("intersted");

            DashboardDto newDashBoard = new DashboardDto();
            newDashBoard.setTotalLeads(totalLead);
            newDashBoard.setTotalCustomer(totalCustomer);
            newDashBoard.setIntrestedCustomer(totalInterstedCustomer);
            return ResponseEntity.status(HttpStatus.OK).body(newDashBoard);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
