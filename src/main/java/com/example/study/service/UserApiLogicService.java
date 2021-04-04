package com.example.study.service;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

//해당 java 클래스는 service로 동작
@Service
public class UserApiLogicService implements CrudInterFace<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    //1.request data를 가져오기

    //2.user 생성

    //3. 생성된 데이터를 기준으로 UserApiResponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request data를 가져오기
        UserApiRequest userApiRequest = request.getData();

        //2.user 생성
        User user = User.builder()
                        .account(userApiRequest.getAccount())
                        .password(userApiRequest.getPassword())
                        .status("REGISTERED")                   //Enum
                        .phoneNumber(userApiRequest.getPhoneNumber())
                        .email(userApiRequest.getEmail())
                        .registeredAt(LocalDateTime.now())
                        .build();

        User newUser = userRepository.save(user);

        //3. 생성된 데이터를 기준으로 UserApiResponse return   > read, update 등에서 쓸일이 많아서 아래에 response 메소드로 따로뺌.


        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(long id) {
        //id -> respository getOne, getById
        Optional<User> optional = userRepository.findById(id);

        //user->userApiResponse return

        return optional
                .map(user -> response(user))
                .orElseGet( ()-> Header.ERROR("데이터 없음") );

        /* 이렇게도 가능.
        * return userRepository.findById(id)
        *                       .map(user -> response(user))
                                .orElseGet( ()-> Header.ERROR("데이터 없음") );
        *
        * */

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. data 가져오기
        UserApiRequest userApiRequest = request.getData();
        //2. id -> user data를 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        //3. update
        return optional.map(user -> {
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;

        })
        .map(user -> userRepository.save((user)))       //update -> 새로운 user 객체 반환
        .map(updateUser ->response(updateUser))                     //userApiresponse 생성됨
        .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        //1. id -> repository -> user
        Optional<User> optional = userRepository.findById(id);

        //2. repository -> delete
        return optional.map(user->{
                    userRepository.delete(user);
                    return Header.OK();
        })
        .orElseGet( ()->Header.ERROR("데이터 없음."));

    }


    //user 객체를 가지고 userApiResponse 객체를 만들어 리턴하는 메소드
    private Header<UserApiResponse> response(User user){
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())       //암호화, 길이를 리턴
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        //Header + data 해서 return
        return Header.OK(userApiResponse);

    }
}
