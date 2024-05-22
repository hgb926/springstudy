package com.study.springstudy.springmvc.chap04.common;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Page {
    private int pageNo; // 클라이언트가 요청한 페이지 번호
    private int amount; // 클라이언트가 요청한 한 페이지당 게시물 목록 수

    // @@NoArgsConstructor 를 지우고 직접 기본생성자 생성
    // 왜? board/list 에서 바로 렌더링 할 수 있게
    public Page() {
        this.pageNo = 1;
        this.amount = 6;
    }

    // @Setter 도 지우고 직접 생성
    // 왜? 클라이언트부터 -28 처럼 이상한 값이 들어오는 것을 막기 위해

    public void setPageNo(int pageNo) {
        if (pageNo < 1 || pageNo > Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }

    public void setAmount(int amount) {
        if (amount < 6 || amount > 60) {
            this.amount = 6;
            return;
        }
        this.amount = amount;
    }

    /*
                만약에 한 페이지에 게시물을 10개씩 렌더링한다면

                1페이지 -> LIMIT 0, 10
                2페이지 -> LIMIT 10, 10
                3페이지 -> LIMIT 20, 10

                만약에 한 페이지에 게시물을 6개씩 렌더링한다면

                1페이지 -> LIMIT 0, 6
                2페이지 -> LIMIT 6, 6
                3페이지 -> LIMIT 12, 6

                만약에 한 페이지에 게시물을 N개씩 렌더링한다면

                1페이지 -> LIMIT 0, N
                2페이지 -> LIMIT 6, N
                3페이지 -> LIMIT 12, N
                M페이지 -> LIMIT (M - 1) * N, N
             */
    public int getPageStart() {
        // mybatis는 PageStart 라는 필드가 없어도
        // getter 형식으로 작성하면 pageStart가 있는 것처럼 사용할 수 있다.
        return (this.pageNo - 1) * this.amount;
    }
}
