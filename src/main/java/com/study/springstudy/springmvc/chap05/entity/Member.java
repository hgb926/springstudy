package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

/*
    account  VARCHAR(50),
    password VARCHAR(150) NOT NULL,
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    auth     VARCHAR(20) DEFAULT 'COMMON',
    reg_date DATETIME    DEFAULT current_timestamp,
    CONSTRAINT pk_member PRIMARY KEY (account)
 */
@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Member {

    private String account;

    @Setter
    private String password;

    private String name;
    private String email;
    private Auth auth;
    private LocalDateTime regDate;
}
