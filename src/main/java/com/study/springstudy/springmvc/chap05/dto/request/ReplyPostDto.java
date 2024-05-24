package com.study.springstudy.springmvc.chap05.dto.request;

import lombok.*;

// 댓글 등록 시 클라이언트가 보낸 데이터를 받는 객체
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyPostDto {

    private String text; // 댓글 내용
    private String author; // 댓글 작성자
    private Long bno; // 원본 글번호

}
