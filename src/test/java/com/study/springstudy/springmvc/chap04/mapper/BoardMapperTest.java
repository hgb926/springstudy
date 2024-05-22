package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardMapperTest {


    @Autowired
    private BoardMapper mapper;


    @Test
    @DisplayName("")
    void insertTest() {
        //given
        for (int i = 1; i <= 300; i++) {
            Board board = new Board();
            board.setTitle("테스트제목"+i);
            board.setWriter("테스트사람"+i);
            board.setContent("내용"+i);
            mapper.save(board);

        }
        //when

        //then
    }

}