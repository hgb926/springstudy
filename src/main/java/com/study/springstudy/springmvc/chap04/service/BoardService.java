package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper mapper;

    // 목록 조회 요청 중간처리
    public List<BoardListResponseDto> findList(Search page) {
        List<Board> boardList = mapper.findAll(page);

        // 조회해온 게시물 리스트에서 각 게시물들의 조회수를 확인하여
        // 조회수가 5이상인 게시물에 특정 마킹
        List<BoardListResponseDto> dtoList = boardList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());

        return dtoList;
    }

    // 등록 요청 중간처리
    public boolean insert(BoardWriteRequestDto dto) {
        Board b = dto.toEntity();
        return mapper.save(b);
    }

    // 삭제 요청 중간처리
    public boolean remove(int boardNo) {
        return mapper.delete(boardNo);
    }

    // 상세 조회 요청 중간처리
    public BoardDetailResponseDto detail(int bno) {
        Board b = mapper.findOne(bno);
        if (b != null) mapper.upViewCount(bno);
        return new BoardDetailResponseDto(b);
    }

    public int getCount() {
        return mapper.count();
    }
}
