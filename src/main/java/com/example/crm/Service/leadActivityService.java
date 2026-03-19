package com.example.crm.Service;

import com.example.crm.Model.LeadEntity;
import com.example.crm.Model.leadActivityEntity;
import com.example.crm.Repository.LeadRepository;
import com.example.crm.Repository.leadActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class leadActivityService {
    private final leadActivityRepository activityRepo;
    private final LeadRepository leadRepo;

    public ResponseEntity<?> createActivity(leadActivityEntity activity, Integer leadId) {
        try {
            Optional<LeadEntity> lead = leadRepo.findById(leadId);
            if (lead.isPresent()) {
                activity.setLead(lead.get());
                activity.setTimeStamp(LocalDateTime.now());
                activityRepo.save(activity);
                return ResponseEntity.status(HttpStatus.CREATED).body("Activity was created ");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("lead was not found");
            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
