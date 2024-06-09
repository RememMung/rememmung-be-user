package rememmung.be_user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.http.ResponseEntity;


public interface AuthCheckerController {
    public ResponseEntity<?> authenticateUser(Map<String, String> tokenMap, HttpServletRequest httpRequest, HttpServletResponse response);

}
