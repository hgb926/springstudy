package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap05.dto.request.ReplyModifyDto;
import com.study.springstudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyListDto;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor // 주입을 위해
@Slf4j
@CrossOrigin // CORS 정책 허용 범위 설정
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    // URL : /api/v1/replise/원본글번호/page/페이지번호     -    GET -> 목록조회
    // @PathVariable : URL에 붙어있는 변수값을 읽는 아노테이션
    @GetMapping("/{bno}/page/{pageNo}") // <- 변수가 들어와야 하므로 중괄호 안에 지정해줌
    public ResponseEntity<?> list(
            @PathVariable long bno // <- bno를 받기 위해
            , @PathVariable int pageNo
    , HttpSession session) {

        if (bno == 0) {
            String message = "글 번호는 0번이 될 수 없습니다.";
            log.warn(message);
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }
        log.info("/api/v1/replies/{} : GET", bno);

        ReplyListDto replies = replyService.getReplies(bno, new Page(pageNo, 10));
//        log.debug("first reply : {}", replies.get(0));
        replies.setLoginUser(LoginUtil.getLoggedInUser(session));

        return ResponseEntity
                .ok()
                .body(replies);
    }

    // 댓글 생성 요청
    // @RequestBody : 클라이언트가 전송한 데이터를 JSON으로 받아서 파싱
    @PostMapping // ("")를 비워두면 위에 @RequestMapping("/api/v1/replies")
    public ResponseEntity<?> posts(@Validated @RequestBody ReplyPostDto dto, BindingResult result
    , HttpSession session) {

        // BindingResult 는 입력값 검증 결과 데이터를 갖고 있는 객체.
        // 입력값이 유효하지 않다면 클라이언트에게 구체적으로 알려줄 객체


        log.info("/api/v1/replies : POST");
        log.debug("parameter: {}", dto);

        if (result.hasErrors()) {
            // BindingResult 에서 에러가 발생했다면,
            // body 에서 클라이언트에게 보낼 에러메세지를 직접 만들 수 있다.
            Map<String, String> errors = makeValidationMessageMap(result);

            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }

        boolean flag = replyService.register(dto, session);

        if (!flag) return ResponseEntity.internalServerError().body("댓글 등록 실패");


        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno(), new Page(1, 10)));
    }


    // 삭제 처리 요청
    @DeleteMapping("/{rno}")
    public ResponseEntity<?> delete(@PathVariable long rno) {
        ReplyListDto dtoList = replyService.remove(rno);

        return ResponseEntity
                .ok()
                .body(dtoList);
    }

    // 댓글 수정 요청
//    @PutMapping  // 전체 수정
//    @PatchMapping   // 일부 수정

    // put + patch 매핑, url 을 비워두면 맨 위에 적은 url로 기본 설정됨.
    // put, patch 를 둘 다 받기 위해
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> modify(
            @Validated @RequestBody ReplyModifyDto dto
            , BindingResult result
    ) {

        log.info("/api/v1/replies : PUT, PATCH");
        log.debug("parameter: {}", dto);

        if (result.hasErrors()) {
            Map<String, String> errors = makeValidationMessageMap(result);

            return ResponseEntity
                    .badRequest()
                    .body(errors);
        }

        ReplyListDto replyListDto = replyService.modify(dto);

        return ResponseEntity.ok().body(replyListDto);

    }




    private Map<String, String> makeValidationMessageMap(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        // 에러 정보가 모여있는 리스트
        List<FieldError> fieldErrors = result.getFieldErrors();
        // errors 에다가 필드들을 담는다.
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

}
