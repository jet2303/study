package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.junit.jupiter.api.Assertions;


import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        //user.setId(1L);      //Auto increasement 이기 때문에 할필요 없음.
        user.setAccount("TestUser03");
        user.setEmail("user3@gmail.com");
        user.setPhoneNumber("010-3333-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("User : " + newUser);
    }

    @Test
    public User read(@RequestParam Long id){

        Optional<User> user = userRepository.findById(2L);
        //Optional<User> user = userRepository.findById(id);
        user.ifPresent(selectUser -> {
            System.out.println("user : " + selectUser);
            System.out.println("email : " + selectUser.getEmail());
        });

        return user.get();
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser -> {
            //셋팅한 값들만 update 해준다.(세팅하지 않았고 기존에 있던 값에는 덮어쓰기 하지 않음)
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method");

            userRepository.save(selectUser);
        });
    }

    //delete url에 삭제될 대상의 정보(id)가 들어오기 때문임
    //@DeleteMapping("/api/user")
    @Test
    //SQL의 TRAN 구문 역할과 같음. 끝나고 롤백됨.
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        //반드시 데이터가 존재해야 하기 때문에(사전 검증 하는 역할)
        Assertions.assertTrue(user.isPresent());        //true

        user.ifPresent(selectUser->{
            //update와 동일하게 (삭제될)기준값을 바꾸면 안됨
            //selectUser.setId(3L);
            //delete는 반환형이 void
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent());     //false
//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재 :" + deleteUser.get());
//        }
//        else{
//            System.out.println("데이터 없음.");
//        }

    }
}
