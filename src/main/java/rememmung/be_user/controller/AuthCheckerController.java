package rememmung.be_user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import rememmung.be_user.dto.TokenData;


public interface AuthCheckerController {
    public ResponseEntity<?> authenticateUser(TokenData tokenData, HttpServletRequest httpRequest, HttpServletResponse response);

}
