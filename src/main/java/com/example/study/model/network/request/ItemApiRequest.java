package com.example.study.model.network.request;

import com.example.study.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemApiRequest {

    private Long id;        //DB index

    private ItemStatus status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;       //소수점때문에

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private Long partnerId;     //어떠한 파트너사?
}
