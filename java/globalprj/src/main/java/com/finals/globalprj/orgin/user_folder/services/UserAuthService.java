package com.finals.globalprj.orgin.user_folder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finals.globalprj.orgin.user_folder.model.UserModel;
import com.finals.globalprj.orgin.user_folder.repository.UserRepository;

@Service
public class UserAuthService {

    @Autowired
    private UserRepository userReposit;

    @Transactional(readOnly = true)
    public UserModel findByIDAndPwd(String userId, String userPwd){
        return userReposit.findByUserIdAndUserPwd(userId, userPwd).orElse(null);
        // Optional은 존재할 수도 있고 존재하지 않을 수도 있음.
    }
    
    @Transactional
    public UserModel signOnNewUser(UserModel newUser){
        return userReposit.save(newUser);
    }

    @Transactional
    public Optional<UserModel> findByUserNo(int userNo){
        return userReposit.findByUserNo(userNo);
    }

    public UserModel findById(String userId) {
        return userReposit.findByUserId(userId).orElse(null);
    }
    
}
//Transactional은 데이터베이스와 연결할 때 데이터베이스와 44

// 주요 메소드

// save(S entity):
// 객체를 저장하거나 업데이트합니다.
// INSERT INTO table ... 또는 UPDATE table ...에 해당합니다.
// 예제: userRepository.save(user);

// saveAll(Iterable<S> entities):
// 여러 객체를 한꺼번에 저장하거나 업데이트합니다.
// 예제: userRepository.saveAll(users);

// findById(ID id):
// 주어진 ID로 엔티티를 찾습니다.
// SELECT * FROM table WHERE id = ...에 해당합니다.
// 예제: Optional<UserModel> user = userRepository.findById(userId);

// existsById(ID id):
// 주어진 ID로 엔티티가 존재하는지 확인합니다.
// SELECT 1 FROM table WHERE id = ...에 해당합니다.
// 예제: boolean exists = userRepository.existsById(userId);

// findAll():
// 모든 엔티티를 조회합니다.
// SELECT * FROM table에 해당합니다.
// 예제: List<UserModel> users = userRepository.findAll();

// findAllById(Iterable<ID> ids):
// 주어진 ID 리스트에 해당하는 모든 엔티티를 조회합니다.
// SELECT * FROM table WHERE id IN (...)에 해당합니다.
// 예제: List<UserModel> users = userRepository.findAllById(userIds);

// count():
// 전체 엔티티의 수를 반환합니다.
// SELECT COUNT(*) FROM table에 해당합니다.
// 예제: long count = userRepository.count();

// deleteById(ID id):
// 주어진 ID에 해당하는 엔티티를 삭제합니다.
// DELETE FROM table WHERE id = ...에 해당합니다.
// 예제: userRepository.deleteById(userId);

// delete(T entity):
// 주어진 엔티티를 삭제합니다.
// DELETE FROM table WHERE id = ...에 해당합니다.
// 예제: userRepository.delete(user);

// deleteAll(Iterable<? extends T> entities):
// 주어진 엔티티들을 모두 삭제합니다.
// DELETE FROM table WHERE id IN (...)에 해당합니다.
// 예제: userRepository.deleteAll(users);

// deleteAll():
// 모든 엔티티를 삭제합니다.
// DELETE FROM table에 해당합니다.
// 예제: userRepository.deleteAll();

// 커스텀 쿼리 메소드
// JpaRepository는 메소드 이름을 통해 자동으로 쿼리를 생성할 수 있습니다. 메소드 이름을 통해 특정 조건을 가진 엔티티를 조회할 수 있습니다.

// findBy{Field}:
// 주어진 필드값에 해당하는 엔티티를 찾습니다.
// 예제: List<UserModel> findByUserName(String userName);는 SELECT * FROM userdatas WHERE user_name = ?에 해당합니다.

// findBy{Field}And{Field}:
// 두 필드값에 해당하는 엔티티를 찾습니다.
// 예제: Optional<UserModel> findByUserIdAndUserPwd(String userId, String userPwd);는 SELECT * FROM userdatas WHERE user_id = ? AND user_pwd = ?에 해당합니다.

// findBy{Field}Or{Field}:
// 두 필드값 중 하나에 해당하는 엔티티를 찾습니다.
// 예제: List<UserModel> findByUserNameOrUserMail(String userName, String userMail);는 SELECT * FROM userdatas WHERE user_name = ? OR user_mail = ?에 해당합니다.

// findBy{Field}OrderBy{Field}Asc/Desc:
// 특정 필드값을 기준으로 정렬하여 엔티티를 찾습니다.
// 예제: List<UserModel> findByUserBanOrderByUserCreatedDesc(boolean userBan);는 SELECT * FROM userdatas WHERE user_ban = ? ORDER BY user_created DESC에 해당합니다.