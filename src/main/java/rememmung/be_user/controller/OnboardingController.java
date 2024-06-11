package rememmung.be_user.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
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
        petInfo.setUser_id(userId);
        try {
            petRepository.save(petInfo);
            return ResponseEntity.ok().body("save");
        }catch (Exception e) {
            return ResponseEntity.status(400).body("bad request");
        }
    }

    @GetMapping("petInfo/get")
    public ResponseEntity<?> getPetOnboardingInfo(HttpSession session) {
        // 이거 토큰으로 해서 내가 파싱해서 쓸지 어떻게 할지 정해야됨.
        String userId = (String) session.getAttribute("userId");
        try{
            petRepository.findByUser_Id("userId");
            return ResponseEntity.ok().body("get");
        }catch (Exception e) {
            return ResponseEntity.status(400).body("bad request");
        }
    }
}
