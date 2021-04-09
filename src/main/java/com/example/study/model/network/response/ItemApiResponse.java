package com.example.study.model.network.response;

import com.example.study.model.enumclass.itemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//Request와 Response는 비슷하지만 따로 정의해두는 이유는 그때그때 달라질수 있기 때문에 나중에 수정할때 공수가 줄어듬
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiResponse {

    private Long id;        //DB index

    private itemStatus status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;       //소수점때문에

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private Long partnerId;     //어떠한 파트너사?
}
