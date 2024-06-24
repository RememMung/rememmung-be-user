package rememmung.be_user.handler;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rememmung.be_user.entity.UserEntity;
import rememmung.be_user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserValidSuccessHandler {
    private final UserRepository userRepository;

    public Map saveUserInfo(Map tokenInfo) {
        Optional<UserEntity> userEntityOptional = userRepository.findByAuthId(tokenInfo.get("id").toString());
        if(userEntityOptional.isPresent()) {
            return ImmutableMap.of("existed",true, "id", userEntityOptional.get().getId());
        }
        String uuid = UUID.randomUUID().toString();
        UserEntity user = UserEntity.builder()
                .id(uuid)
                .authId(tokenInfo.get("id").toString())
                .build();

        userRepository.save(user);

        return ImmutableMap.of("existed", false, "id", uuid);
    }
}
