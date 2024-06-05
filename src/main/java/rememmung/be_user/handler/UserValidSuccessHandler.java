package rememmung.be_user.handler;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rememmung.be_user.entity.UserEntity;
import rememmung.be_user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserValidSuccessHandler {
    private final UserRepository userRepository;

    public UserEntity getUserInfo(Map tokenInfo) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(tokenInfo.get("id").toString());
        if(userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        }

        UserEntity user = UserEntity.builder()
                .userId(tokenInfo.get("id").toString())
                .build();

        userRepository.save(user);

        return user;
    }
}
