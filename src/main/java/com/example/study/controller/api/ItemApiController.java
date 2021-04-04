package com.example.study.controller.api;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterFace<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")        //  /api/item/
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")     //    /api/item/{id}        //@PathVariable
    public Header<ItemApiResponse> read(@PathVariable long id) {
        return null;
    }

    @Override
    @PutMapping("")         //      /api/item
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}")      //      /api/item/{id}      //@PathVariable
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
