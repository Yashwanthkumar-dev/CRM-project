package com.example.crm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64; // 👈 1. Indha import-ah add panni iruken
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    private String number;
    private String email;
    private String password;
    private String role;
    private String imageName;
    private String imageType;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LeadEntity> leads;


    public String getImageData() {
        if (this.imageData != null) {
            return Base64.getEncoder().encodeToString(this.imageData);
        }
        return null;
    }
}