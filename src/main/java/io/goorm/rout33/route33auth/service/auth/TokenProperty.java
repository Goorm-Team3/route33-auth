package io.goorm.rout33.route33auth.service.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties("token")
public record TokenProperty(
        String secretKey,
        long accessTokenExpirationMillis,
        long refreshTokenExpirationMillis
) {
}
