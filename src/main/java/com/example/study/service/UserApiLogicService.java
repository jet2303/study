package com.example.study.service;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//해당 java 클래스는 service로 동작
@Service
public class UserApiLogicService implements CrudInterFace<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    //1.request data를 가져오기

    //2.user 생성

    //3. 생성된 데이터를 기준으로 UserApiResponse return

    @Override
    public Header<UserApiResponse> create(UserApiRequest request) {
        return null;
    }

    @Override
    public Header<UserApiResponse> read(long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update(UserApiRequest request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
