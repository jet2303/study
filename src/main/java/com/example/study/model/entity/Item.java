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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderDetailList","partner"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder                //매개변수가 많은 경우 여러가지 갯수를 가지는 매개변수 생성자를 한꺼번에 선언처리를 해줄수있음.
@Accessors(chain = true)        //Update를 하는경우 chain 패턴을 통해 한줄로 처리 가능
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private  String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

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


    //Item N : 1 Partner
    @ManyToOne
    //private Long partnerId;
    private Partner partner;


    //Item 1 : N OrderDetail
    //mappedBy = orderDetail 엔티티의 변수와 동일 해야함
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;


//    //1:N
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
//    private List<OrderDetail> orderDetailList;

}
