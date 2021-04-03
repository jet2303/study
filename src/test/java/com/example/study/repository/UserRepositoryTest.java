package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;

import jdk.vm.ci.meta.Local;
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
        String account = "Test03";
        String password = "Test03";
        String ststus = "REGISTERD";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(ststus);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        //객체를 생성시 builder 패턴 사용
        User u = User.builder().account(account).password(password).status(ststus).email(email)
                      .build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        //chain 패턴을 이용해 업데이트
        user.setEmail("")
                .setPhoneNumber("")
                .setStatus("");

        User u = new User().setAccount("").setEmail("").setPassword("");
        //if 로 처리로 nullPointerException 처리, 또는 Optional로 회피피
       user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("-----------주문묶음----------------------");
            System.out.println(orderGroup.getRevName());
            System.out.println(orderGroup.getTotalPrice());
            System.out.println(orderGroup.getRevAddress());
            System.out.println(orderGroup.getTotalQuantity());

            System.out.println("-----------주문상세----------------------");
            orderGroup.getOrderDetailList().forEach(orderDetail->{

            System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
            System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
            System.out.println("주문상품 : " +orderDetail.getItem().getName());
            System.out.println("고객센터 번호 : " +orderDetail.getItem().getPartner().getCallCenter());
            System.out.println("주문의 상태 : " + orderDetail.getStatus());
            System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());


           });
        });
        Assertions.assertNotNull(user);
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

