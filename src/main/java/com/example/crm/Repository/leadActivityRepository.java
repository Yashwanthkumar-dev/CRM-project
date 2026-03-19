package com.example.crm.Repository;

import com.example.crm.Model.leadActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface leadActivityRepository extends JpaRepository<leadActivityEntity,Integer> {
    List<leadActivityEntity> findFirst5ByOrderByTimeStampDesc();
}
