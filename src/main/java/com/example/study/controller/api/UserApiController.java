package com.example.study.controller.api;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j          //테스트해볼때 사용
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterFace<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userApiLogicService.orderInfo(id);
    }

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id",direction = Sort.Direction.DESC) Pageable pageable){   //오름차순 내림차순
        log.info("{}",pageable);
        return userApiLogicService.search(pageable);
    }

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
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")      //      /api/user/{id}
    public Header delete(@PathVariable Long id) {
        log.info("id :{}", id);
        return userApiLogicService.delete(id);
    }
}
