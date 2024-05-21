package com.study.springstudy.springmvc.chap04.repository;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql = "SELECT * FROM tbl_board";
        return template.query(sql, (rs, rowNum) -> new Board(rs));
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
        return template.queryForObject(sql,
                (rs, rowNum) -> new Board(rs),
                boardNo
        );
    }

    @Override
    public boolean save(Board board) {
        String sql = "INSERT INTO tbl_board " +
                "(title, content, writer) " +
                "VALUES (?,?,?)";
        return template.update(sql,
                board.getTitle(),
                board.getContent(),
                board.getWriter()) == 1;
    }

    @Override
    public boolean delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE board_no = ?";
        return template.update(sql, boardNo) == 1;
    }

    @Override
    public void upViewCount(int boardNo) {
        String sql = "UPDATE tbl_board " +
                "SET view_count = view_count + 1 " +
                "WHERE board_no = ?";
        template.update(sql, boardNo);
    }
}
