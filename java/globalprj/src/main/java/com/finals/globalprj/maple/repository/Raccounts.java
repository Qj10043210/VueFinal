package com.finals.globalprj.maple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finals.globalprj.maple.model.*;

public interface Raccounts extends JpaRepository<Maccounts ,Integer>{
    
    
    @Query(value = "SELECT COUNT(*) FROM maccounts WHERE name = :reqId", nativeQuery = true)
    int checkId(@Param("reqId") String reqId);

    
    @Query(value = "SELECT COUNT(*) FROM maccounts WHERE name = :reqId AND password = :reqPwd", nativeQuery = true)
    int checkPwd(@Param("reqId") String reqId, @Param("reqPwd") String reqPwd);
    
}
