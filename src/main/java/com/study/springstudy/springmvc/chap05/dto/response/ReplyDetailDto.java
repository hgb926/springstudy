package com.study.springstudy.springmvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyDetailDto {

    private long rno;
    private String text;
    private String writer;
    // profile, get 요청 어디서 확인?
    private String profile;

//    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime createAt;
    private String account; // 댓글 작성자 계정명

    // 엔터티를 DTO로 변환하는 생성자
    public ReplyDetailDto(Reply reply) {
        this.rno = reply.getReplyNo();
        this.text = reply.getReplyText();
        this.writer = reply.getReplyWriter();
        this.createAt = reply.getReplyDate();
        this.account = reply.getAccount();
        this.profile = reply.getProfile();
    }

}
