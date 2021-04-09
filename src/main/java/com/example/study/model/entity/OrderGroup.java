package com.example.study.model.entity;


import com.example.study.model.enumclass.OrderType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user","orderDetailList"})       //한쪽만 ToString 제외시켜줘도 되지만 공통적으로 하기위해 함
@EntityListeners(AuditingEntityListener.class)
@Builder                //매개변수가 많은 경우 여러가지 갯수를 가지는 매개변수 생성자를 한꺼번에 선언처리를 해줄수있음.
@Accessors(chain = true)        //Update를 하는경우 chain 패턴을 통해 한줄로 처리 가능
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;       //주문의 형태 - 일괄/개별

    private String revAddress;

    private String revName;

    private String paymentType;     //카드/현금 결제

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    //private LocalDateTime registeredAt;

    //private LocalDateTime unregisteredAt;

    //최초 생성시에 값이 자동으로 반영됨.
    @CreatedDate            //component package에 LonginUserAuditorAware 의 getCurrentAuditor()메서드에 설정된것이 여기에 적용됨
    private LocalDateTime createdAt;

    @CreatedBy              //component package에 LonginUserAuditorAware 의 getCurrentAuditor()메서드에 설정된것이 여기에 적용됨
    private String createdBy;


    //최종 수정시에 값이 자동으로 반영됨됨
    @LastModifiedDate       //component package에 LonginUserAuditorAware 의 getCurrentAuditor()메서드에 설정된것이 여기에 적용됨
    private LocalDateTime updatedAt;

    @LastModifiedBy         //component package에 LonginUserAuditorAware 의 getCurrentAuditor()메서드에 설정된것이 여기에 적용됨
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
