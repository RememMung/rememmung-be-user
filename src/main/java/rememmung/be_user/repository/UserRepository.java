package rememmung.be_user.repository;

import java.util.Optional;
import java.util.OptionalInt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rememmung.be_user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByAuthId(String authId);

}
