package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;
import org.checkerframework.checker.units.qual.N;

import java.time.LocalDateTime;

/*
	reply_no INT(8) PRIMARY KEY auto_increment,
    reply_text VARCHAR(1000) NOT NULL,
    reply_writer VARCHAR(100) NOT NULL,
    reply_date DATETIME default current_timestamp,
    board_no INT(8),

    constraint fk_reply
    foreign key (board_no)
    references tbl_board (board_no)
    on delete cascade
 */
@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    private long replyNo;
    private String replyText;
    private String replyWriter;
    private LocalDateTime replyDate;
    private long boardNo;
    private String account;

    // 조인해서 받아온 댓글 작성자의 프로필url
    private String profile;

}
