package com.example.crm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeadSourceDTO {
    private String source;
    private Long count;
}
