package com.example.crm.Repository;

import com.example.crm.Model.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity , Integer> { Long countByFollowUps(String followUps);
    Optional<LeadEntity>findByemail(String email);
}
