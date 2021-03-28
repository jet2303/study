package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();

        user.setAccount("TestUser02");

        user.setEmail("user3@gmail.com");
        user.setPhoneNumber("010-2222-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser2");

        User newUser = userRepository.save(user);
        System.out.println("User : " + newUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findById(7L);

        //7번 유저를 가져와서 select, 방금 연결시킨 detailList를 가져와서, 스트림으로 출력, 각각의 detail이 가지고 있는 itemid를 가져옴
        user.ifPresent(selectUser->{
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
            });
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("admin");

            //JPA 가 id 있는지 확인, 있으면 update
            //hibernate로 확인해보면 select후에 update SQL 실행함.
            userRepository.save(selectUser);
            });
    }

    @Test
    @Transactional
    @DeleteMapping("/api/user")
    //delete는 URL에 삭제될 대상이 파라미터로 넘어감   //public void delete(@RequestParam Long id){
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        //값이 존재하는지 검증.
        //Assertions.assertTrue(user.isPresent());        //true

        user.ifPresent(selectUser->{

            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(2L);

        //삭제되었는지 검증
        Assertions.assertFalse(deleteUser.isPresent());     //false
//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재: ");
//        }
//        else{
//            System.out.println("데이터 삭제됨.");
//        }

    }

}

