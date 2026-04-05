package com.example.crm.Repository;

import com.example.crm.DTO.LeadSourceDTO;
import com.example.crm.Model.LeadEntity;
import com.example.crm.Model.leadActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, Integer> {
    Long countByFollowUps(String followUps);

    Optional<LeadEntity> findByemail(String email);

//    Optional<leadActivityEntity> findById(Integer id);

    @Query("select l.source , count(l) from LeadEntity  l  group by l.source ")
    List<Object[]> getLeadCountBySource();

    Long countByFollowUpsNot(String followUps);
    Long countByNextFollowDate(String nextFollowUps);

    @Query("select new com.example.crm.DTO.LeadSourceDTO(l.source,count(l)) from LeadEntity l group by l.source")
    List<LeadSourceDTO> getLeadSourceStat();

//    --- current month leads
    @Query(value = "select count(*) from lead_entity where month(created_at) = month(current_date())" ,nativeQuery = true)
    Long countCurrentMonthLeads();

//     ---- previous month leads
    @Query(value = "select count(*) from lead_entity where month(created_at) = month(current_date()-interval 1 month)",nativeQuery = true)
    Long countPreviousMonthLeads();
}
