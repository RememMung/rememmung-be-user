package rememmung.be_user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rememmung.be_user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<UserEntity> findById(Long aLong);
}
