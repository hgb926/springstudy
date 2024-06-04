package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;

/*
    reaction_id   INT(8) PRIMARY KEY AUTO_INCREMENT,
    board_no      INT(8)                   NOT NULL,
    account       VARCHAR(50)              NOT NULL,
    reaction_type ENUM ('LIKE', 'DISLIKE') NOT NULL,
    reaction_date DATETIME DEFAULT current_timestamp,
 */
@Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reaction {
    private long reactionId;
    private long boardNo;
    private String account;
    private ReactionType reactionType;
    private LocalDateTime reactionDate;
}
