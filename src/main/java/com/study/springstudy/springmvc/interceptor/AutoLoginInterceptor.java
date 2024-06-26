package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.chap05.entity.Member;
import com.study.springstudy.springmvc.chap05.mapper.MemberMapper;
import com.study.springstudy.springmvc.chap05.service.MemberService;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class AutoLoginInterceptor implements HandlerInterceptor {

    private final MemberMapper memberMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 사이트에 들어오면 자동로그인 쿠키를 가지고 있는지 확인
        // WebUtils는 Spring의 객체
        Cookie autoLoginCookie
                = WebUtils.getCookie(request, LoginUtil.AUTO_LOGIN_COOKIE);

        // 2. 자동로그인 쿠키가 있고, 로그인이 안되어있다면 사이트 로그인 처리
        if (autoLoginCookie != null
                && !LoginUtil.isLoggedIn(request.getSession())) {

            // 3. 쿠키에 들어있는 랜덤값을 읽어야 함.
            String sessionId = autoLoginCookie.getValue();

            // 4. sessionId로 회원정보를 조회
            Member foundMember = memberMapper.findMemberBySessionId(sessionId);

            // 5. 회원이 정상조회 되었고 자동로그인 만료시간 이전이라면
            //    사이트 로그인 처리(세션에 DTO세팅)를 수행
            if (foundMember != null
                    && LocalDateTime.now().isBefore(foundMember.getLimitTime())) {
                MemberService.maintainLoginState(request.getSession(), foundMember);
            }
        }
        return true;
    }
}

// 내가 쓴 댓글이 아니면 수정 삭제 안뜨게.
