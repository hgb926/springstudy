package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

/*
    id        INT PRIMARY KEY auto_increment,
    account   VARCHAR(50),
    board_no  INT,
    view_time DATETIME
 */
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewLog {
    
    private long id;
    private String account;
    private long boardNo;
    private LocalDateTime viewTime;
}
