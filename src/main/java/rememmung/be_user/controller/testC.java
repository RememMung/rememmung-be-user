package rememmung.be_user.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testC {
    @PostMapping("/test")
    public boolean testc(HttpSession session) {
        // 세션에서 사용자 ID 조회
        String userId = (String) session.getAttribute("id");

        // Spring Security 컨텍스트에서 인증 정보 확인
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(userId);
        System.out.println(auth);
        return true;
    }
}
