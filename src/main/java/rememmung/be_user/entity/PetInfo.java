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
    @NotNull
    private String species;
    @NotNull
    private String personality;
    // 사진 정보 추가
    private String sex;
    private String birthday;
    private String farewellday;
    private String likeInfo;
    private String hate;
    private String skill;

}
