package rememmung.be_user.controller;

import jakarta.servlet.http.HttpServletRequest;
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
import rememmung.be_user.handler.UserValidSuccessHandler;
import rememmung.be_user.service.AuthService;
import rememmung.be_user.util.RedisService;

@RestController
@RequiredArgsConstructor
public class KakaoAuthController implements AuthCheckerController {

    @Value("${KAKAO_TOKEN_VALIDATION_URI}")
    private String kakaoAuthUri;

    private final AuthService authService;
    private final RedisService redisService;
    private final UserValidSuccessHandler userValidSuccessHandler;
    @Override
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/auth/kakao")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> tokenMap, HttpServletRequest httpRequest) {
        String accessToken = tokenMap.get("accessToken");

        Map tokenInfo = authService.getTokenInfo(accessToken, kakaoAuthUri);

        if (tokenInfo.get("statusCode").equals(200)) {
            String userId = tokenInfo.get("id").toString();

            redisService.saveSession(userId);

            Authentication auth = new UsernamePasswordAuthenticationToken(userId, null,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println(tokenInfo);
            // 기존 유저인지 확인
            return ResponseEntity.ok().body(userValidSuccessHandler.getUserInfo(tokenInfo));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
    }
}
