package rememmung.be_user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@AllArgsConstructor
@Builder
public class PetInfo {
    @Id
    private Long id; //user 고유 id
    private String userId;
    private String type;
    private String name;
    private String gender;
    private String birth;
    private String farewall;
    private String fondof;
    private String dislike;
    private String skill;
    private String createdAt;
    private String updatedAt;
}
