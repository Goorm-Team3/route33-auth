package io.goorm.rout33.route33auth.controller;


import io.goorm.rout33.route33auth.model.dto.TokenRefreshRequestDto;
import io.goorm.rout33.route33auth.model.dto.TokenRefreshResponseDto;
import io.goorm.rout33.route33auth.model.dto.UserLoginRequestDto;
import io.goorm.rout33.route33auth.model.dto.UserLogoutResponseDto;
import io.goorm.rout33.route33auth.service.UserRepository;
import io.goorm.rout33.route33auth.service.UserService;
import io.goorm.rout33.route33auth.service.auth.TokenPair;
import io.goorm.rout33.route33auth.service.auth.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 관련 컨트롤러
 */
@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;
    private final UserRepository userRepository;


    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto requestDto) {
        TokenPair tokenPair = userService.login(requestDto);
        return ResponseEntity.ok(tokenPair);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenRefreshRequestDto requestDto){

        UserLogoutResponseDto responseDto = userService.logout(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<TokenRefreshResponseDto> refreshToken(@RequestBody TokenRefreshRequestDto requestDto){
        TokenPair tokenPair = tokenService.refreshTokenPair(requestDto.refreshToken());
        return ResponseEntity.ok(TokenRefreshResponseDto.from(tokenPair));
    }
}
