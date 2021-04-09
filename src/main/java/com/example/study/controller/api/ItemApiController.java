package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterFace;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/item")
//public class ItemApiController implements CrudInterFace<ItemApiRequest, ItemApiResponse> {
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse> {
    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @PostConstruct
    public void init(){
        //static 메서드와 유사하게 실행됨.
        this.baseService = itemApiLogicService;
    }

// CrudController 추상클래스에 메서드가 정의 되어있음.
//    @Override
//    @PostMapping("")        //  /api/item/
//    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
//        return itemApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")     //    /api/item/{id}        //@PathVariable
//    public Header<ItemApiResponse> read(@PathVariable long id) {
//
//        return itemApiLogicService.read(id);        //itemApiLoginService와 연결
//    }
//
//    @Override
//    @PutMapping("")         //      /api/item
//    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
//
//        return itemApiLogicService.update(request);     //itemApiLoginService와 연결
//    }
//
//    @Override
//    @DeleteMapping("{id}")      //      /api/item/{id}      //@PathVariable
//    public Header delete(@PathVariable Long id) {
//
//        return itemApiLogicService.delete(id);
//    }

    //controller 단 -> service 단으로 풀어가기.
}
