package rememmung.be_user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rememmung.be_user.entity.PetInfo;
import rememmung.be_user.entity.UserEntity;

@Repository
public interface PetRepository extends JpaRepository<PetInfo, Long> {

    @Override
    PetInfo getReferenceById(Long aLong);
    Optional<PetInfo> findByUser_Id(String userId);

}
