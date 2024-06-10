package com.study.springstudy.springmvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserDto {

    private Long id;

    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

    private Properties properties;

    // 내부 클래스
    @Getter @ToString
    public class Properties {
        private String nickname;

        @JsonProperty("profile_image")
        private String profileImage;

    }
}
