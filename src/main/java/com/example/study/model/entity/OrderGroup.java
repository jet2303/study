package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user","orderGroup"})       //한쪽만 ToString 제외시켜줘도 되지만 공통적으로 하기위해 함
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType;       //주문의 형태 - 일괄/개별

    private String revAddress;

    private String revName;

    private String paymentType;     //카드/현금 결제

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    //private LocalDateTime registeredAt;

    //private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    //외래키
    //ordergroup입장에선 many : 1
    @ManyToOne
    //아래의 변수명은 User엔티티의 mappedBy와 동일해야함.
    private User user;


    //orderGROUP 1  : N orderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;



}
