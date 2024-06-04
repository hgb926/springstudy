package com.study.springstudy.springmvc.chap05.dto.response;

import com.study.springstudy.springmvc.chap04.common.PageMaker;
import lombok.*;

import java.util.List;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*
    {
        "replies": [
            {}, {}, {}
        ]
    }
 */
public class ReplyListDto {

    /*
        [
            {}, {}, {}
        ]
     */
    private List<ReplyDetailDto> replies;
    private PageMaker pageInfo; // 필드명이 key값
    @Setter
    private LoginUserInfoDto loginUser;
}
