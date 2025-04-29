package io.goorm.rout33.route33auth.model.dto;


import io.goorm.rout33.route33auth.service.auth.TokenPair;

public record TokenRefreshResponseDto(
        String accessToken,
        String refreshToken
) {
    public static TokenRefreshResponseDto from(TokenPair tokenPair){
        return new TokenRefreshResponseDto(tokenPair.accessToken(), tokenPair.refreshToken());
    }
}
