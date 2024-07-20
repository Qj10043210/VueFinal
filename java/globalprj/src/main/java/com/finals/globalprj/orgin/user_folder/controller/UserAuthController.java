package com.finals.globalprj.orgin.user_folder.controller;

import org.springframework.web.bind.annotation.RestController;

import com.finals.globalprj.orgin.encompass.model.TokenResponse;
import com.finals.globalprj.orgin.encompass.service.JwtUtil;
import com.finals.globalprj.orgin.user_folder.model.UserModel;
import com.finals.globalprj.orgin.user_folder.services.UserAuthService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<TokenResponse> signin(@RequestBody UserModel authRequest) {        
        UserModel user = userAuthService.findByIDAndPwd(authRequest.getUserId(), authRequest.getUserPwd());
        if(user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else{
            String token = jwtUtil.generateToken(user);
            TokenResponse response = new TokenResponse(token);
                        return ResponseEntity.ok(response);
        }

    }
    @PostMapping("/signon")
    public ResponseEntity<?> signon(@RequestBody UserModel authRequest) {        
        try {
            if(
            authRequest.getUserId() != null
        &&  authRequest.getUserPwd() != null
        &&  authRequest.getUserName() != null
        &&  authRequest.getUserMail() != null
        ){
            Optional<UserModel> currentUser = userAuthService.findByUserNo(authRequest.getUserNo());
            if(currentUser.isPresent()){
                UserModel updateUser = currentUser.get();
                updateUser.setUserId(authRequest.getUserId());
                updateUser.setUserPwd(authRequest.getUserPwd());
                updateUser.setUserName(authRequest.getUserName());
                updateUser.setUserMail(authRequest.getUserMail());
                UserModel newUser = userAuthService.signOnNewUser(updateUser);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            }else{
                UserModel newUser = userAuthService.signOnNewUser(authRequest);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            }            
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/claims/{userNo}")
    public ResponseEntity<?> claimByUserNo(@PathVariable("userNo") int userNo){
        try {
            Optional<UserModel> user = userAuthService.findByUserNo(userNo);            
            if(!user.isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }else{
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/check/{userId}")
    public ResponseEntity<?> checkByUserId(@PathVariable("userId") String userId){
        try {
            System.out.println("userId "+userId);
            if(userId == null){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            UserModel user = userAuthService.findById(userId);
            if(user==null){
                return ResponseEntity.status(HttpStatus.OK).build();
                //하지만 성공이죠
            }else{
                return ResponseEntity.status(HttpStatus.IM_USED).build();
                //하지만 실패죠
            }
            
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        


    }
    
    
}

class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
class AuthResponse {
    private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
    
}