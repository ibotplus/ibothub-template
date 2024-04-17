package com.ibothub.love.template.auth;

import com.ibothub.love.template.exception.ResultCode;
import com.ibothub.love.template.model.vo.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class KbsAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.debug("[{}] UNAUTHORIZED", request.getServletPath());
        AuthUtil.flushResponse(response, ResponseEntity.failure(ResultCode.UNAUTHORIZED));
    }
}
