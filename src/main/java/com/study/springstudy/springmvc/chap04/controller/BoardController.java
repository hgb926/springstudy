package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);
    //    private final BoardRepository repository;
    private final BoardService service;
    private final BoardService boardService;

    // 1. 목록 조회 요청 (/board/list : GET)
    @GetMapping("/list")
    public String list(@ModelAttribute("s") Search page, Model model, @RequestParam(defaultValue = "10")int a) { // 3. 파라미터 타입 바꿔주기
        System.out.println("/board/list GET");

        // 서비스에게 조회 요청 위임
        List<BoardListResponseDto> bList = service.findList(page);

        // 페이지 정보를 생성하여 JSP 에게 전송
        PageMaker maker = new PageMaker(page, service.getCount(page));

        // 3. JSP파일에 해당 목록데이터를 보냄
        model.addAttribute("bList", bList);
        model.addAttribute("maker", maker);
//        model.addAttribute("s", page);



        return "board/list";
    }

    // 2. 게시글 쓰기 양식 화면 열기 요청 (/board/write : GET)
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/write GET! ");
        return "board/write";
    }

    // 3. 게시글 등록 요청 (/board/write : POST)
    // -> 목록조회 요청 리다이렉션
    @PostMapping("/write")
    public String write(BoardWriteRequestDto dto, HttpSession session) {
        System.out.println("/board/write POST! ");

        // 1. 브라우저가 전달한 게시글 내용 읽기
        System.out.println("dto = " + dto);

        service.insert(dto, session);

        return "redirect:/board/list";
    }

    // 4. 게시글 삭제 요청 (/board/delete : GET)
    // -> 목록조회 요청 리다이렉션
    @GetMapping("/delete")
    public String delete(int bno, HttpServletRequest request) {
        System.out.println("/board/delete GET");

        String loggedUserAccount = LoginUtil.getLoggedUserAccount(request.getSession());
        BoardDetailResponseDto detail = boardService.detail(bno);

        if (detail != null && detail.getAccount().equals(loggedUserAccount)) {
            boardService.remove(bno);
            return "redirect:/board/list";
        } else {
            return   "redirect:/members/sign-in?message=not-authorized";
        }
    }

    // 5. 게시글 상세 조회 요청 (/board/detail : GET)
    @GetMapping("/detail")
    public String detail(Model model, int bno,
                         HttpServletRequest request) {
        System.out.println("/board/detail GET");

        // 1. 상세조회하고 싶은 글번호를 읽기
        System.out.println("bno = " + bno);

        // 2. 데이터베이스로부터 해당 글번호 데이터 조회하기
        BoardDetailResponseDto dto = service.detail(bno);

        // 3. JSP파일에 조회한 데이터 보내기
        model.addAttribute("bbb", dto);

        // 4. 요청 헤더를 파싱하여 이전 페이지의 주소를 얻어냄
        String ref = request.getHeader("Referer");
        model.addAttribute("ref", ref);

        return "board/detail";
    }


}
