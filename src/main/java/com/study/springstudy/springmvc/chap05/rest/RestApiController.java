package com.study.springstudy.springmvc.chap05.rest;

import com.study.springstudy.database.chap01.Person;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest")
//@Controller
//@ResponseBody // 클래스 상단에 붙히면 메서드마다 일일이 붙히지 않아도 됨.
@RestController // <- Controller, ResponseBody 두개가 통합된 것.
@RequiredArgsConstructor // boardService 주입
public class RestApiController {

    private final BoardService boardService;

    @GetMapping("/hello")
    @ResponseBody // 서버가 클라이언트에게 순수한 데이터를 전달할 때 사용
    // jsp를 전송하지 않고 데이터만 전송해줌.
    public String hello() {
        // ...

        return "안녕 메롱";
    }

    @GetMapping("/hobby")
    public List<String> hobby() {
        List<String> hobbies = List.of("태권도", "장기", "댄스");
        return hobbies;
    }

    @GetMapping(value = "/person", produces = "application/json") // 생략
    public Person person() {
        Person p = new Person(100, "핑구", 10);
        return p;
    }

    @GetMapping("/board")
    public Map<String, Object> board() {

        List<BoardListResponseDto> list = boardService.findList(new Search());

        HashMap<String, Object> map = new HashMap<>();
        map.put("page", new PageMaker(new Page(), boardService.getCount(new Search())));
        map.put("articles", list);

        return map;
    }

    /*
         RestController에서 리턴타입을 ResponseEntity를 쓰는 이유

         - 클라이언트에게 응답할 때는 메시지 바디안에 들어 있는
           데이터도 중요하지만
         - 상태코드와 헤더정보를 포함해야 한다
         - 근데 일반 리턴타입은 상태코드와 헤더정보를 전송하기 어렵다
     */

    @GetMapping("/people")
    public ResponseEntity<?> people() {
        Person p1 = new Person(111, "딸기", 30);
        Person p2 = new Person(222, "루피", 40);
        Person p3 = new Person(333, "뽀로로", 50);
        List<Person> people = List.of(p1, p2, p3);

        // 같이 보낼 정보들을 담을 수 있다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("mypet", "cat");
        headers.add("money", "230");

        return ResponseEntity
                .status(200) // <- 응답 코드
                .headers(headers)
                .body(people); //  <- return 할 데이터
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi(
            @RequestParam(required = false) Double cm,
            @RequestParam(required = false) Double kg) {

        // 입력값이 없으면 상태코드 400 전달
        if (cm == null || kg == null) {
            return ResponseEntity
                    .badRequest()
                    .body("키와 몸무게를 전달하세요!");
        }

        double m = cm / 100;
        double bmi = kg / (m * m);

        return ResponseEntity
                .ok()
                .body(bmi);
    }

}
