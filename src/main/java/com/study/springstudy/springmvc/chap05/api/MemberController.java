package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.request.SignUpDto;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    // ModelAndView 를 사용하면 jsp도 포워딩 가능하고
    // 동기 비동기 둘다 가능, JSON 날릴땐 @ResponesBody

    /*
    @GetMapping("/hello")
    public String hello() {
        return ("board/write");
    }

    ↓ ↑ 서로 동일하다

    public ModelAndView hello() {
        return new ModelAndView("board/write);
    }
     */

    private final MemberService memberService;

    // 회원 가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp() {
        log.info("/members/sign-up GET : forwarding to sign-up.jsp");
//        return "members/sign-up";
        // return의 URL이 컨트롤러의 기본 경로면 void도 쌉가능
    }

    // 회원가입 요청 처리
    @PostMapping("/sign-up")
    public String signUp(@Validated SignUpDto dto) {
        log.info("/members/sign-up POST");
        log.debug("parameter: {}", dto);

        boolean flag = memberService.join(dto);
        return flag ? "redirect:/board/list" : "redirect:/members/sign-up";
    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody // Rest Controller가 아니므로 비동기 처리 시 @ResponseBody
    public ResponseEntity<?> check(String type, String keyword) {
        boolean flag = memberService.checkIdentity(type, keyword);
        log.debug("{} 중복 체크 결과? {}", type, flag);
        return ResponseEntity.ok().body(flag);
    }

}
