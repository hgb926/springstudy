package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardService boardService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("Board interceptor executing!");

        String redirectUri = request.getRequestURI();
        log.debug("redirect uri: {}", redirectUri);

        if (!LoginUtil.isLoggedIn(request.getSession())) {
            response.sendRedirect("/members/sign-in?message=login-required&redirect=" + redirectUri);
            return false;
        }

        // 삭제 요청이 들어오면 서버에서 한번 더 관리자인지, 자기가 쓴 글인지 체크
        String bnoParam = request.getParameter("bno");
        if (bnoParam != null) {
            int boardNo = Integer.parseInt(bnoParam);
            BoardDetailResponseDto boardDetail = boardService.detail(boardNo);

            if (boardDetail != null) {
                String boardWriter = boardDetail.getAccount();
                String loggedInUser = LoginUtil.getLoggedUserAccount(request.getSession());

                if (!boardWriter.equals(loggedInUser)) {
                    response.sendRedirect("/board/list?message=not-authorized");
                    return false;
                }
            }
        }

        return true;
    }
}