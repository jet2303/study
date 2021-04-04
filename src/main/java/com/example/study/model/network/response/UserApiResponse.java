package com.example.study.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiResponse {

    private Long id;

    private String account;

    private String password;        //암호화(마스킹) 했기 때문에 request와 response를 나누어 만든것(비슷하지만)

    private String status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

}
