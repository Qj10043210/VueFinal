package com.finals.globalprj.maple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finals.globalprj.maple.Mall;
import com.finals.globalprj.maple.model.*;

public interface Rboard extends JpaRepository<Mboard ,Integer>{
    
    
    @Query(value = "SELECT * FROM mboard WHERE btype = :reqN ORDER BY bcreatedat DESC", nativeQuery = true)
    List<Mboard> getBoard(@Param("reqN") int reqN);

    @Modifying
    @Query(value = "UPDATE mboard SET bplaying = 1 WHERE pname = :pname", nativeQuery = true)
    void readyBoard(@Param("pname") String pname);

    
    @Query(value = "SELECT COUNT(*) FROM mboard", nativeQuery = true)
    int checkSevenBoardCount();

    @Modifying
    @Query(value = "DELETE FROM mboard WHERE pname = :pname", nativeQuery = true)
    void leaveBoard(@Param("pname") String pname);

    @Modifying
    @Query(value = "UPDATE mboard SET pname = :pname WHERE lroom = :lroom", nativeQuery = true)
    void leaveGiveBoard(@Param("pname") String pname, @Param("lroom") int lroom);

    @Modifying
    @Query(value = "DELETE FROM mboard ORDER BY bid LIMIT 1", nativeQuery = true)
    void checkSevenBoard1();

    @Modifying
    @Query(value = "DELETE FROM mboard ORDER BY bid LIMIT 0", nativeQuery = true)
    void checkSevenBoard0();
        
}
