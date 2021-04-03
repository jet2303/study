package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    //공통적으로 들어가는 값들
    //api 통신시간
    //@JsonProperty("transaction_time")
    private LocalDateTime transactionTime;         // ISO format,  YYYY-MM-DD HH:mm:ss

    //api 응답 코드
    private String resultCode;

    //api 부가 설명
    private String description;

    //generic으로
    private T data;


    //정상 OK
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                    .transactionTime(LocalDateTime.now())
                    .resultCode("OK")
                    .description("OK")
                    .build();
    }

    //DATA OK
    public static <T> Header<T> OK(T data){                 //매개변수로 data
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }
    //비정상 ERROR
    public static <T> Header<T> ERROR(String description){      //alert 메시지가 들어갈수도 있음
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
