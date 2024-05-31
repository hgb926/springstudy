package com.study.springstudy.springmvc.chap04.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardFindAllDto {

    /*
            B.board_no,
            B.title,
            B.content,
            B.writer,
            B.reg_date_time,
            B.view_count,
            COUNT(R.reply_no) AS reply_count
        select 결과를  받는 dto
     */

    private long boardNo;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDateTime;
    private int viewCount;
    private int replyCount;
    private String account;
}
