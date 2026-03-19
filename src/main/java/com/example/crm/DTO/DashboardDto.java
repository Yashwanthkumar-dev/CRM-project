package com.example.crm.DTO;

import com.example.crm.Model.leadActivityEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashboardDto {
    private Long totalLeads;
    private Long totalCustomer;
    private Long intrestedCustomer;
    private List<leadActivityEntity> recentActivities;
}
