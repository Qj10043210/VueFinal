package com.finals.globalprj.maple.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.finals.globalprj.maple.model.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public interface Rplayers extends JpaRepository<Mplayers ,Integer>{
    
    
    
    @Query(value = "SELECT * FROM mplayers WHERE name = :lid", nativeQuery = true)
    List<Mplayers> toChar(@Param("lid") String lid);

    
    @Query(value = "SELECT COUNT(*) FROM mplayers WHERE pname = :checkName", nativeQuery = true)
    int checkName(@Param("checkName") String checkName);

    @Modifying
    @Query(value = "DELETE FROM mplayers WHERE pname = :reqName", nativeQuery = true)
    int goodbye(@Param("reqName") String reqName);

    
    @Query(value = "SELECT * FROM mplayers WHERE pname = :pname", nativeQuery = true)
    List<Mplayers> getUserInfo(@Param("pname") String pname);

    @Modifying
    @Query(value = "UPDATE mplayers SET pcurhp = :pcurhp WHERE pname = :pname", nativeQuery = true)
    void updateHp(@Param("pcurhp") double pcurhp, @Param("pname") String pname);

    
    @Query(value = "SELECT pluk FROM mplayers WHERE pname = :pname", nativeQuery = true)
    int getLuck(@Param("pname") String pname);
    
    @Modifying
    @Query(value = "UPDATE mplayers SET pexp = pexp + :pexp WHERE pname = :pname", nativeQuery = true)
    void updateExp(@Param("pexp") double pexp, @Param("pname") String pname);

    @Modifying
    @Query(value = "INSERT INTO mplayers (name, pcurhp, pdex, pface, phair, pint, pluk, pmaxhp, pname, pstr) VALUES (:name,  :pcurhp, :pdex, :pface, :phair, :pint, :pluk, :pmaxhp, :pname, :pstr)",
        nativeQuery = true)
void postPlayer(@Param("name") String name,
                
                @Param("pcurhp") double pcurhp,
                @Param("pdex") int pdex,
               
                @Param("pface") int pface,
                @Param("phair") int phair,
                @Param("pint") int pint,
               
                @Param("pluk") int pluk,
                @Param("pmaxhp") double pmaxhp,
                @Param("pname") String pname,
                
                @Param("pstr") int pstr);
}
