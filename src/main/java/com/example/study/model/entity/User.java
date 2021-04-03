package com.example.study.model.entity;

//엔티티는 DB의 테이블 명과 동일하면됨. 주로 API 통신규격에서는 snake case, java에서는 camel case를 주로 사용하는 차이점.


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor     //모든 매개변수를 가지는 생성자 생성.
@NoArgsConstructor      //기본 생성자.
@ToString(exclude = {"orderGroupList"})     //조인을 걸어준 변수,  상호 참조시 lombok의 ToString이 무한반복때문에 오버플로 발생.
@Entity     //==table
//@Table(name = "User")   //클래스와 테이블의 이름이 같다면 생략가능
@EntityListeners(AuditingEntityListener.class)
@Builder                //매개변수가 많은 경우 여러가지 갯수를 가지는 매개변수 생성자를 한꺼번에 선언처리를 해줄수있음.
@Accessors(chain = true)        //Update를 하는경우 chain 패턴을 통해 한줄로 처리 가능
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "account") 이것도 실제 테이블의 컬럼명과 변수명이 동일 하기 때문에 생략 가능.
    private String account;

    private String password;

    private String status;

    private String email;

    //테이블에서는 phone_number이지만 JPA에서 자동으로 매칭 시켜줌.
    private String phoneNumber;

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

    //User : OrderGroup
    // 1   :  N

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;


//    //.LAZY = 지연 로딩 - 해당 테이블 하나만 FROM 해서 SELECT - 시간 적게 걸림.
//    //.EAGER = 즉시 로딩 - 연관된 테이블 전부 LEFT OUTER JOIN 해서 SELECT - 시간 많이 걸림.(보통 테이블 하나만 걸릴때 사용)
//    //User 테이블 입장에서는 1명의 유저, N개의 구매목록이므로 @OneToMany
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")   //mappedBy는 orderDetail에 있는 변수명(user)과 동일 해야함.
//                                                            //
//
//    private List<OrderDetail> orderDetailList;      //OrderDetail의 변수 user와 매칭 시킨다.
//                                                    //이렇게 user 클래스와 OrderDetail에서 1:N관계가 만들어진것.
}
