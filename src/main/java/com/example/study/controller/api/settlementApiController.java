package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterFace;
import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.request.SettlementApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.model.network.response.SettlementApiResponse;
import com.example.study.service.SettlementApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//CREATE TABLE `study`.`settlement` (
//        `user_id` VARCHAR(20) NOT NULL,
//        `price` INT NOT NULL,
//        PRIMARY KEY (`user_id`));



@RestController
@RequestMapping("/settlement/")
public class settlementApiController  {

    @Autowired
    private SettlementApiLogicService settlementApiLogicService;


    @GetMapping("{id}")
    public Header<SettlementApiResponse> read(String account) {

        return settlementApiLogicService.read(account);
    }

}
