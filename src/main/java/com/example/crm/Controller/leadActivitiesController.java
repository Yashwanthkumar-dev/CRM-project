package com.example.crm.Controller;

import com.example.crm.Model.leadActivityEntity;
import com.example.crm.Service.leadActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class leadActivitiesController {
    @Autowired
    private leadActivityService activityService;
    @PostMapping("/activity/{leadId}")
    public ResponseEntity<?> createActivity(@RequestBody leadActivityEntity activity, @PathVariable Integer leadId){
        return activityService.createActivity(activity,leadId);
    }
}
