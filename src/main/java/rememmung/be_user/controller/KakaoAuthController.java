package rememmung.be_user.controller;

import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rememmung.be_user.service.AuthService;

@RestController
@RequiredArgsConstructor
public class KakaoAuthController implements AuthCheckerController {

    @Value("${KAKAO_TOKEN_VALIDATION_URI}")
    private String kakaoAuthUri;

    private final AuthService authService;
    @Override
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/auth/kakao")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> tokenMap) {
        String accessToken = tokenMap.get("accessToken");

        // 토큰 유효성 검증 로직 (예: 카카오 서버에 토큰 유효성 요청)
        boolean isValidToken = authService.validateToken(accessToken, kakaoAuthUri);

        if (isValidToken) {
            // 성공적인 인증 후 사용자 정보 조회 및 세션 생성
            Authentication auth = new UsernamePasswordAuthenticationToken(accessToken, null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(auth);
            return ResponseEntity.ok().body("User authenticated");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
    }


}
