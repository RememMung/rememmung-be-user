package rememmung.be_user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@AllArgsConstructor
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    private Long id;

    private String email;
}
