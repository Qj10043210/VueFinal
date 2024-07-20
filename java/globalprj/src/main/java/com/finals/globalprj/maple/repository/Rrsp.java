package com.finals.globalprj.maple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finals.globalprj.maple.Mall;
import com.finals.globalprj.maple.model.*;


@Transactional
public interface Rrsp extends JpaRepository<Mrsp ,Integer>{
    @Modifying
    @Query(value = "INSERT INTO mrsp (pname) SELECT :lid WHERE NOT EXISTS (SELECT 1 FROM mrsp WHERE pname = :lid)", nativeQuery = true)
    void checkScoreRSP(@Param("lid") String lid);
    
    
    @Query(value = "SELECT * FROM mrsp WHERE pname = :pname", nativeQuery = true)
    List<Mrsp> getScoreRSP(@Param("pname") String pname);

    @Modifying
    @Query(value = "UPDATE mrsp SET rwin = rwin + 1 WHERE pname = :pname", nativeQuery = true)
    void updateScoreWin(@Param("pname") String pname);

    @Modifying
    @Query(value = "UPDATE mrsp SET rlose = rlose + 1 WHERE pname = :pname", nativeQuery = true)
    void updateScoreLose(@Param("pname") String pname);

    @Modifying
    @Query(value = "UPDATE mrsp SET rdraw = rdraw + 1 WHERE pname = :pname", nativeQuery = true)
    void updateScoreDraw(@Param("pname") String pname);
}
