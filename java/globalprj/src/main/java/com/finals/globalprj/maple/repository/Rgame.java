package com.finals.globalprj.maple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finals.globalprj.maple.model.*;

public interface Rgame extends JpaRepository<Mgame ,Integer>{
    @Modifying
    @Query(value = "INSERT INTO mgame (pname, btype, rolr, ghand, gnum) VALUES (:pname, :btype, :rolr, :ghand, :gnum)", nativeQuery = true)
    void insertHand(@Param("pname") String pname, @Param("btype") int btype, @Param("rolr") String rolr, @Param("ghand") String ghand, @Param("gnum") int gnum);

    
    @Query(value = "SELECT * FROM mgame WHERE gnum = :gnum ORDER BY gid DESC LIMIT 2", nativeQuery = true)
    List<Mgame> giveHand(@Param("gnum") int gnum);

    @Modifying
    @Query(value = "DELETE FROM mgame WHERE pname = :sources", nativeQuery = true)
    void deleteHand(@Param("sources") String sources);
    
    
}
