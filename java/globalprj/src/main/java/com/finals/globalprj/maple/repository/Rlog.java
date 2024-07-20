package com.finals.globalprj.maple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.finals.globalprj.maple.model.*;

@Transactional
public interface Rlog extends JpaRepository<Mlog ,Integer>{
    void deleteByLtypeAndPname(String ltype, String pname);
    void deleteByPname(String pname);
    void deleteByLtypeAndLroom(String ltype, int lroom);

    
    @Query(value = "SELECT COUNT(*) FROM mlog WHERE lroom = :lroom", nativeQuery = true)
    int checkRecentN(@Param("lroom") int lroom);

    
    
    @Query(value = "SELECT pname FROM mlog WHERE lroom = :lroom LIMIT 1", nativeQuery = true)
    String checkRecentName(@Param("lroom") int lroom);

    
    @Query(value = "SELECT ltype FROM mlog WHERE lroom = :lroom ORDER BY lid DESC LIMIT 1", nativeQuery = true)
    String checkRecentType(@Param("lroom") int lroom);

    
    @Query(value = "SELECT * FROM mlog WHERE lroom = :lroom ORDER BY lid DESC LIMIT 1", nativeQuery = true)
    List<Mlog> checkRecentT(@Param("lroom") int lroom);

    @Modifying
    @Query(value = "UPDATE mlog SET ltype = 'EXIT' WHERE lid = :lid", nativeQuery = true)
    void updateType(@Param("lid") int lid);

    
}
