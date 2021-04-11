package com.example.study.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagenation {

    private Integer totalpages;     //총 몇개 페이지

    private Long totalElements;     //총 유저수

    private Integer currentPage;        //현재 페이지

    private Integer currentElements;        //현재 몇개의 데이터가 내려갔는지.
}
