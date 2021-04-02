package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.utilities.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();



        orderDetail.setStatus("준비중");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        //어떤 상품을?
        orderDetail.setItemId(1L);

        //어떤 장바구니에?
        orderDetail.setOrderGroupId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assertions.assertNotNull(newOrderDetail);
    }

}
