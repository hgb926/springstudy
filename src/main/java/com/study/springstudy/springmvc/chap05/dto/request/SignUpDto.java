package com.study.springstudy.springmvc.chap05.dto.request;


import com.study.springstudy.springmvc.chap05.entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// 회원가입할때 사용할 dto
@Getter @ToString
@Setter // 동기 방식이라 setter 필요
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class SignUpDto {

    // 자바도 정규표현식이 가능하다.
    // 클라이언트와 서버 둘다 검증을 강하게 받아야 한다.
    @NotBlank(message = "아이디는 필수값입니다.")
    @Size(min = 4, max = 14, message = "아이디는 4~14글자")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 포함해야 합니다.")
    private String account;

    @NotBlank
    private String password;

    @NotBlank
    @Size(min = 2, max = 6)
    private String name;

    @NotBlank
    @Email
    private String email;

    // 프로필 사진 데이터가 추가로 옴 - 0605
    private MultipartFile profileImage; // 필드명은 input태그의 name.


    public Member toEntity() {
        return Member.builder()
                .account(this.account)
                .password(this.password)
                .email(this.email)
                .name(this.name)
                .build();
    }
}
