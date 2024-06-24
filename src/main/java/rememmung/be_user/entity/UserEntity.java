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
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    private String id;

    @Column(name = "user_name", nullable = false, length = 191)
    private String userName;

    @Column(name = "auth_id", nullable = false, length = 191)
    private String authId;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "auth_type", nullable = false)
//    private AuthType authType;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(3)")
    private LocalDateTime deletedAt;

    @Column(name = "email", nullable = false, length = 191)
    private String email;
}
