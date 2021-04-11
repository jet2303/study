package com.example.study.service;


import com.example.study.ifs.CrudInterFace;
import com.example.study.model.entity.Settlement;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.SettlementApiRequest;
import com.example.study.model.network.response.SettlementApiResponse;
import com.example.study.repository.SettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettlementApiLogicService  {

    @Autowired
    private SettlementRepository settlementRepository;

    public Header<SettlementApiResponse> read(String account) {
         Optional<User> optional = settlementRepository.findByAccount(account);

//        return findAccount;




        return null;
    }

}
