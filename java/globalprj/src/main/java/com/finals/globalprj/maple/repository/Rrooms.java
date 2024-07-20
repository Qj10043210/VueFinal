package com.finals.globalprj.maple.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finals.globalprj.maple.model.*;

public interface Rrooms extends JpaRepository<Mrooms ,Integer>{
 
    void deleteAllByPname(String pname);

    
    @Query(value = "SELECT * FROM mrooms WHERE lroom = :lroom AND btype = :btype", nativeQuery = true)
    List<Mrooms> getCoonectedUser(@Param("lroom") int lroom, @Param("btype") int btype);

    
    @Query(value = "SELECT * FROM mrooms WHERE rore = 1 AND lroom IN (SELECT lroom FROM mrooms WHERE rore = 1 GROUP BY lroom HAVING COUNT(*) > 1) AND lroom NOT IN (SELECT lroom FROM mboard WHERE bplaying = 1)", nativeQuery = true)
    List<Mrooms> loadReadyPlayer();

    int countByLroom(int Lroom);

    List<Mrooms> findByLroom(int Lroom);

    @Modifying
    @Query(value="UPDATE mrooms SET rore = :rore WHERE pname = :pname", nativeQuery = true)
    void readySitu(@Param("rore")boolean rore, @Param("pname")String pname);

    List<Mrooms> findByLroomAndBtype(int lroom, int Btype);
}
