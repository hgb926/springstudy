package com.study.springstudy.springmvc.chap05.dto.response;


import com.study.springstudy.springmvc.chap05.entity.Member;
import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class LoginUserInfoDto {

    // 클라이언트에 보낼 정제된 정보
    private String account;
    private String nickName;
    private String email;
    private String auth; // enum -> String으로 바꿔서 보냄

    public LoginUserInfoDto(Member member) {
        this.account = member.getAccount();
        this.email = member.getEmail();
        this.nickName = member.getName();
//        this.auth = member.getAuth().toString();
//        Auth.ADMIN
        this.auth = member.getAuth().name();
//        ADMIN
    }

}
