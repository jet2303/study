package com.example.study.service;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        return null;
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
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
