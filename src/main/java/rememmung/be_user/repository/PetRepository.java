package rememmung.be_user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rememmung.be_user.entity.PetInfo;

@Repository
public interface PetRepository extends JpaRepository<PetInfo, Long> {

    Optional<PetInfo> findByUserId(String userId);

}
