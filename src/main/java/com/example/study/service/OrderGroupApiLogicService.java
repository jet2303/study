package com.example.study.service;

import com.example.study.ifs.CrudInterFace;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderGroupApiLogicService implements CrudInterFace<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;      //User의 정보가 필요하기 때문.

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                                        .status(body.getStatus())
                                        .orderType(body.getOrderType())
                                        .revAddress(body.getRevAddress())
                                        .revName(body.getRevName())
                                        .paymentType(body.getPaymentType())
                                        .totalPrice(body.getTotalPrice())
                                        .totalQuantity(body.getTotalQuantity())
                                        .orderAt(LocalDateTime.now())
                                        .arrivalDate(LocalDateTime.now())
                                        .user(userRepository.getOne(body.getUserId()))      //누가 주문했는지
                                        .build();

        OrderGroup newOrederGroup = orderGroupRepository.save(orderGroup);      //저장이된, 인덱스 id가 들어간 새로운 엔티티가 반환됨.

        return response(newOrederGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(long id) {

        return orderGroupRepository.findById(id)
                 // .map(this::response)          //데이터가 있는경우       //this->현재 클래스
                .map(orderGroup -> response(orderGroup))
                .orElseGet(()->Header.ERROR("데이터없음"));   //데이터가 없는경우

    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        return orderGroupRepository.findById(body.getId())
                .map(orderGroup -> {
                    orderGroup.setStatus(body.getStatus())
                            .setOrderType(body.getOrderType())
                            .setRevAddress(body.getRevAddress())
                            .setRevName(body.getRevName())
                            .setPaymentType(body.getPaymentType())
                            .setTotalPrice(body.getTotalPrice())
                            .setTotalQuantity(body.getTotalQuantity())
                            .setOrderAt(body.getOrderAt())
                            .setArrivalDate(body.getArrivalDate())
                            .setUser(userRepository.getOne(body.getUserId()));
                            //.setUser(userRepository.getOne(body.getUserId()))

                    return orderGroup;
                })          //데이터 있는경우
                .map(changeOrderGroup->orderGroupRepository.save(changeOrderGroup))
                .map(newOrderGroup->response(newOrderGroup))
                .orElseGet(()->Header.ERROR("데이터 없음"));       //데이터 없는경우
    }

    @Override
    public Header delete(Long id) {

        return orderGroupRepository.findById(id)
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(body);
    }
}
