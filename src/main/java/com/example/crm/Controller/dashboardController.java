package com.example.crm.Controller;

import com.example.crm.Service.dashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class dashboardController {
    @Autowired
    private dashboardService dashService;

    @GetMapping("/dashboardStatus")
    public ResponseEntity<?>getDashboardDetails(){
        return dashService.getDashboardDetails();
    }
}
