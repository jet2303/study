package com.example.study.controller.api;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j          //테스트해볼때 사용
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterFace<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")        //  /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}, {}",request,"ABC");     //-> request.toString(), "ABC"    //simple logging system
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")     //  /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id") long id) {
        log.info("read: {} ",id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")         //      /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")      //      /api/user/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
