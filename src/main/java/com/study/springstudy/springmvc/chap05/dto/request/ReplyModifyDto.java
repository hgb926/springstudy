package com.study.springstudy.springmvc.chap05.dto.request;


import com.study.springstudy.springmvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
// 클라이언트가 수정할 때 보내는 데이터
public class ReplyModifyDto {

    @NotNull
    private Long rno; // 수정할 댓글의 댓글 번호
    @NotBlank
    private String newText; // 새로운 댓글 내용
    @NotNull
    private Long bno; // 수정 완료 후 새로운 목록을 조회 하기 위해

    // 엔터티로 변환하는 메서드
    public Reply toEntity() {
        return Reply.builder()
                .replyText(this.newText)
                .replyNo(this.rno)
                .boardNo(this.bno)
                .build();
    }
}