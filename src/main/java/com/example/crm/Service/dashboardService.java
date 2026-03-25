package com.example.crm.Service;

import com.example.crm.DTO.DashboardDto;
import com.example.crm.DTO.leadSourceDto;
import com.example.crm.Model.LeadEntity;
import com.example.crm.Model.leadActivityEntity;
import com.example.crm.Repository.CustomerRepository;
import com.example.crm.Repository.LeadRepository;
import com.example.crm.Repository.leadActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class dashboardService {
    private final LeadRepository leadRepo;
    private final CustomerRepository customerRepo;
    private final leadActivityRepository activityRepo;

    public ResponseEntity<?> getDashboardDetails() {
        try {
            Long totalLead = leadRepo.count();
            Long totalCustomer = customerRepo.count();

            Long totalInterstedCustomer = leadRepo.countByFollowUps("interested");

            DashboardDto newDashBoard = new DashboardDto();
            newDashBoard.setTotalLeads(totalLead);
            newDashBoard.setTotalCustomer(totalCustomer);
            newDashBoard.setIntrestedCustomer(totalInterstedCustomer);

            List<leadActivityEntity> latestFive = activityRepo.findFirst5ByOrderByTimeStampDesc();
            newDashBoard.setRecentActivities(latestFive);
            return ResponseEntity.status(HttpStatus.OK).body(newDashBoard);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public List<leadSourceDto> getLeadSourceAnalytics() {
        List<Object[]> rawData = leadRepo.getLeadCountBySource();
        Long totalLeads = leadRepo.count();

        List<leadSourceDto> sourceList = new ArrayList<>();
        if (totalLeads == 0) return sourceList;
        for (Object[] row : rawData) {
            String source = (String) row[0];
            long count = (Long) row[1];
            double percentage = ((double) count / totalLeads) * 100;


            sourceList.add(new leadSourceDto(source, count, (int) Math.round(percentage)));
        }
        return sourceList;
    }
}
