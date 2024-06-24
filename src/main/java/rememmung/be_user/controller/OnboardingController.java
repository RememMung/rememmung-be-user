package rememmung.be_user.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rememmung.be_user.entity.PetInfo;
import rememmung.be_user.repository.PetRepository;

@RestController
@RequiredArgsConstructor
public class OnboardingController {
    private final PetRepository petRepository;

    @GetMapping("/petInfo/save")
    public ResponseEntity<?> savePetOnboardingInfo(@RequestBody PetInfo petInfo, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if(userId.equals(null) || SecurityContextHolder.getContext().getAuthentication() == null){
            return ResponseEntity.status(401).body("Invalid Session");
        }

        petInfo.setUserId(userId);
        try {
            petRepository.save(petInfo);
            return ResponseEntity.ok().body("save");
        }catch (Exception e) {
            return ResponseEntity.status(400).body("bad request");
        }
    }

    @GetMapping("petInfo/get")
    public ResponseEntity<?> getPetOnboardingInfo(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId.equals(null) || SecurityContextHolder.getContext().getAuthentication() == null){
            return ResponseEntity.status(401).body("Invalid Session");
        }
        try{
            return ResponseEntity.ok().body(petRepository.findByUserId("userId"));
        }catch (Exception e) {
            return ResponseEntity.status(400).body("bad request");
        }
    }
}
