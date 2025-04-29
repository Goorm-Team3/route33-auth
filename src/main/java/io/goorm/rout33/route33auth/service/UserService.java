package io.goorm.rout33.route33auth.service;

import io.goorm.rout33.route33auth.exception.CustomException;
import io.goorm.rout33.route33auth.model.User;
import io.goorm.rout33.route33auth.model.dto.TokenRefreshRequestDto;
import io.goorm.rout33.route33auth.model.dto.UserLoginRequestDto;
import io.goorm.rout33.route33auth.model.dto.UserLogoutResponseDto;
import io.goorm.rout33.route33auth.service.auth.TokenPair;
import io.goorm.rout33.route33auth.service.auth.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;


    /**
     * 로그인 진행
     *
     */
    public TokenPair login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByLoginId(requestDto.getLoginId())
                .orElseThrow(() -> new CustomException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        return tokenService.createTokenPair(user.getUserId());
    }

    public UserLogoutResponseDto logout(TokenRefreshRequestDto requestDto) {
        User user = userRepository.findByRefreshToken(requestDto.refreshToken())
                .orElseThrow(() -> new CustomException("존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST));
        user.updateRefreshToken(null);
        userRepository.save(user);
        UserLogoutResponseDto responseDto = new UserLogoutResponseDto("로그아웃 완료");


        return responseDto;
    }
}
