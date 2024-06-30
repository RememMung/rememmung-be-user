package rememmung.be_user.controller;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rememmung.be_user.dto.PetDTO;
import rememmung.be_user.entity.PetInfo;
import rememmung.be_user.repository.PetRepository;

@RestController
@RequiredArgsConstructor
public class OnboardingController {
    private final PetRepository petRepository;

    @PostMapping("/petInfo/save")
    public ResponseEntity<?> savePetOnboardingInfo(@RequestBody PetDTO petInfo, HttpSession session) {
        String userId = (String) session.getAttribute("id");
        if(userId == null || SecurityContextHolder.getContext().getAuthentication() == null){
            return ResponseEntity.status(401).body("Invalid Session");
        }
        PetInfo petEntity = PetInfo.builder()
                        .userId(userId)
                .name(petInfo.getName())
                .dislike(petInfo.getDislike().stream()
                        .collect(Collectors.joining(",")))
                .skill(petInfo.getSkill().stream()
                        .collect(Collectors.joining(",")))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .gender(petInfo.getGender())
                .birthday(petInfo.getBirthday())
                .farewallday(petInfo.getFarewallday())
                .favorites(petInfo.getFavorites().stream()
                        .collect(Collectors.joining(",")))
                .personality(petInfo.getPersonality().stream()
                        .collect(Collectors.joining(",")))
                .species(petInfo.getSpecies())
                .build();
        try {
            petRepository.save(petEntity);
            return ResponseEntity.ok().body("save");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("bad request");
        }
    }

    @GetMapping("petInfo/get")
    public ResponseEntity<?> getPetOnboardingInfo(HttpSession session) {
        String userId = (String) session.getAttribute("id");
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
