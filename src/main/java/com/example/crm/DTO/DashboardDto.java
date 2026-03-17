package com.example.crm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashboardDto {
    private Long totalLeads;
    private Long totalCustomer;
    private Long intrestedCustomer;
}
