package com.ibothub.love.template.util;

import com.ibothub.love.template.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {

    private static PrivateKey privateKey;
    private static PublicKey publicKey;
    private static JwtConfig jwtConfig;

    static {
        //加载 ibothub-api.jks文件
        try (InputStream inputStream =
                     Thread.currentThread().getContextClassLoader().getResourceAsStream("ibothub-jwt.jks")) {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, "ibothub".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "ibothub".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成Token
     *
     * @param userId 用户ID
     */
    public static String generateJwt(String userId) {
        // calculate the date
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime now = LocalDateTime.now();
        Date nowDate = Date.from(now.atZone(zoneId).toInstant());
        Date expireDate = Date.from(
                now.plusMinutes(jwtConfig.getExpireInMinute())
                        .atZone(zoneId)
                        .toInstant()
        );

        String jwt = Jwts.builder()
                .issuer(jwtConfig.getIssuer())
                .subject(userId)
                .audience().add(userId)
                .and()
                .expiration(expireDate)
                .notBefore(nowDate)
                .issuedAt(nowDate)
                .id(userId)
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
        // jwt前面一般都会加Bearer
        return jwtConfig.getTokenHead() + jwt;
    }

    /**
     * 旧jwt到期续签
     */
    public static Optional<String> renewJwt(String oldJwt) {
        Claims claims = ofClaims(oldJwt);

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = LocalDateTime.ofInstant(claims.getExpiration().toInstant(), zoneId);
        boolean needRenew = Duration.between(now, expiration).toMinutes() <= jwtConfig.getRefreshRemainLeftMinute();
        return Optional.ofNullable(needRenew ? generateJwt(claims.getId()) : null);
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     *
     * @return 数据声明
     */
    public static Claims ofClaims(String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token.substring(jwtConfig.getTokenHead().length()))
                .getPayload();
    }


    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        JwtUtil.jwtConfig = jwtConfig;
    }

}