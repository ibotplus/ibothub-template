package com.ibothub.love.template.auth;

import com.ibothub.love.template.config.JwtConfig;
import com.ibothub.love.template.exception.ResultCode;
import com.ibothub.love.template.model.vo.ResponseEntity;
import com.ibothub.love.template.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class KbsLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private JwtConfig jwtConfig;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException {
        try {
            Optional.of(request.getHeader(jwtConfig.getAuthHeader()))
                    .filter(StringUtils::isNotBlank)
                    .map(JwtUtil::ofClaims)
                    .map(Claims::getId)
                    .ifPresent(s -> redisTemplate.delete(s));

            AuthUtil.flushResponse(response, ResponseEntity.ok(true));
        } catch (Exception e) {
            log.error("on logout with error: {}", e.getMessage());
            AuthUtil.flushResponse(response, ResponseEntity.failure(ResultCode.JWT_ILLEGAL_TOKEN, e.getMessage()));
        }
    }
}