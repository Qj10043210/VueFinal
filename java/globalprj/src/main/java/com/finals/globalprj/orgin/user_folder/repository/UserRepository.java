package com.finals.globalprj.orgin.user_folder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finals.globalprj.orgin.user_folder.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

    Optional<UserModel> findByUserIdAndUserPwd(String userId, String userPwd);
    Optional<UserModel> findByUserNo(int userNo);
    Optional<UserModel> findByUserId(String userId);
    
}