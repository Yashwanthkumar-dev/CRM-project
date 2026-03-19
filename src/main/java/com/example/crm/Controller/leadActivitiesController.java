package com.example.crm.Controller;

import com.example.crm.Model.leadActivityEntity;
import com.example.crm.Service.leadActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class leadActivitiesController {
    @Autowired
    private leadActivityService activityService;
    @PostMapping("/activity/{leadId}")
    public ResponseEntity<?> createActivity(@RequestBody leadActivityEntity activity, @PathVariable Integer leadId){
        return activityService.createActivity(activity,leadId);
    }
}
