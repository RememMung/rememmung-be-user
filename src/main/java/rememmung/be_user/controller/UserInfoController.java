package rememmung.be_user.controller;

import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rememmung.be_user.entity.UserEntity;
import rememmung.be_user.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class UserInfoController {
    private final UserRepository userRepository;
    @GetMapping("userInfo/get")
    public ResponseEntity<?> getUserInfo(HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if(userId.equals(null) || SecurityContextHolder.getContext().getAuthentication() == null){
            return ResponseEntity.status(401).body("Invalid Session");
        }
        try{
            Optional<UserEntity> userEntityOptional = userRepository.findByAuthId(userId);
            if(userEntityOptional.isPresent()) {
                return ResponseEntity.status(200).body(userEntityOptional.get());
            }
        }catch (Exception e) {
            return ResponseEntity.status(400).body("bad request");
        }finally {
            return ResponseEntity.status(404).body("Server Error");
        }
    }
}
