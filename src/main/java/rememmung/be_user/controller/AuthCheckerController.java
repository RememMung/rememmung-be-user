package rememmung.be_user.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;


public interface AuthCheckerController {
    public ResponseEntity<?> authenticateUser(Map<String, String> tokenMap);

}
