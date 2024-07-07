package rememmung.be_user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pet_info")
public class PetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 191)
    private String userId;

    @Column(nullable = false, length = 191)
    private String name;

    @Column(length = 191)
    private String dislike;

    @Column(length = 191)
    private String skill;

    @Column(nullable = false, columnDefinition = "TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Gender gender;

    @Column(columnDefinition = "TIMESTAMP(3)")
    private LocalDateTime birthday;

    @Column(columnDefinition = "TIMESTAMP(3)")
    private LocalDateTime farewallday;

    @Column(length = 191)
    private String favorites;

    @Column(nullable = false, length = 191)
    private String personality;

    @Column(nullable = false, length = 191)
    private String species;

    // Getters and Setters

    public enum Gender {
        MALE, FEMALE
    }
}
