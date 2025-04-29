package io.goorm.rout33.route33auth.service.auth;

public record TokenPair(
        String accessToken,
        String refreshToken
) {
}