package com.example.crm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadAnalyticsDTO {
    private Long totalLeads;
    private Long activeLead;
    private Long todayFollows;
}
