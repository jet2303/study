package com.example.study.model.entity;


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

@Data
@NoArgsConstructor  //기본 생성자
@AllArgsConstructor //모든 생성자
@Entity     //order_detail에 자동 연결
@ToString(exclude = {"orderGroup","item"})       //상호참조 에러 방지
@EntityListeners(AuditingEntityListener.class)
@Builder                //매개변수가 많은 경우 여러가지 갯수를 가지는 매개변수 생성자를 한꺼번에 선언처리를 해줄수있음.
@Accessors(chain = true)        //Update를 하는경우 chain 패턴을 통해 한줄로 처리 가능
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;



    private BigDecimal totalPrice;

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


    //OrderDetail N : 1 Item
    @ManyToOne
    //private Long itemId;
    private Item item;

    //OrderDetail N : 1 OrderGroup
    //private Long orderGroupId;
    @ManyToOne
    private OrderGroup orderGroup;


//    //OrderDetail 입장에선 N개의 구매목록, 1명의 User
//    @ManyToOne
//    //private Long userId;
//    private User user;      //어노테이션을 통한 @ManyToOne을 사용시 Long -> User, userId->user 로 선언하면 Hibernate가 user_id로
//                            //매칭시켜줌.
//
//
//    @ManyToOne
//    //private Long itemId;
//    private Item item;
}
