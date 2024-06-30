package rememmung.be_user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rememmung.be_user.dto.TokenData;
import rememmung.be_user.handler.UserValidSuccessHandler;
import rememmung.be_user.service.AuthService;

@RestController
@RequiredArgsConstructor
public class KakaoAuthController implements AuthCheckerController {

    @Value("${KAKAO_TOKEN_VALIDATION_URI}")
    private String kakaoAuthUri;

    private final AuthService authService;
    private final UserValidSuccessHandler userValidSuccessHandler;

    @Override
    @PostMapping("/api/auth/kakao")
    public ResponseEntity<?> authenticateUser(@RequestBody TokenData tokenData, HttpServletRequest httpRequest, HttpServletResponse response) {
        String accessToken = tokenData.getAccessToken();

        Map tokenInfo = authService.getTokenInfo(accessToken, kakaoAuthUri);

        try {
            if (tokenInfo.get("statusCode").equals(200)) {
                Map existed = userValidSuccessHandler.saveUserInfo(tokenInfo, accessToken);

                Authentication authentication = new UsernamePasswordAuthenticationToken(existed.get("id"), null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                final HttpSession session = httpRequest.getSession();
                session.setAttribute("id", existed.get("id").toString());
                session.setMaxInactiveInterval(3600);

                return ResponseEntity.ok().body(Map.of("existed", existed.get("existed")));

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Server Error");
        }
    }
}
