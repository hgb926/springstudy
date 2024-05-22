package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

// 2. 클래스 만들기

@Getter @Setter @ToString
@EqualsAndHashCode
public class Search extends Page {

    // 검색어, 검색 조건
    private String keyword, type;

    public Search() {
        this.keyword = "";
    }

}
