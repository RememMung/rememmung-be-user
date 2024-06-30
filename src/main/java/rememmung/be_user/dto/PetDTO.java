package rememmung.be_user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import rememmung.be_user.entity.PetInfo.Gender;

@Data
public class PetDTO {

    private String userId;
    private String name;
    private List<String> dislike;
    private List<String> skill;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Gender gender;
    private LocalDateTime birthday;
    private LocalDateTime farewallday;
    private List<String> favorites;
    private List<String> personality;
    private String species;
}
