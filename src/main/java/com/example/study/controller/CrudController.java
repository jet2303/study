package com.example.study.controller;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.network.Header;
import com.example.study.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<Req, Res, Entity> implements CrudInterFace<Req, Res> {

    //protected CrudInterFace<Req, Res> baseService;      //상속받은것만 사용
    @Autowired(required = false)
    protected BaseService<Req, Res, Entity> baseService;      //리팩토링

    @Override
    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")         //pathvalueable
    public Header<Res> read(@PathVariable long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
