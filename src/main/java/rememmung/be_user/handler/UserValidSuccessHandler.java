package rememmung.be_user.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rememmung.be_user.entity.UserEntity;
import rememmung.be_user.repository.UserRepository;
import rememmung.be_user.util.RestService;

@Service
@RequiredArgsConstructor
public class UserValidSuccessHandler {
    private final UserRepository userRepository;
    private final RestService restService;
    private final ObjectMapper objectMapper;

    @Value("${KAKAO_TOKEN_USER_INFO}")
    private String APIURL;
    public Map saveUserInfo(Map tokenInfo, String accessToken) throws IOException {
        Optional<UserEntity> userEntityOptional = userRepository.findByAuthId(tokenInfo.get("id").toString());
        if(userEntityOptional.isPresent()) {
            return ImmutableMap.of("existed",true, "id", userEntityOptional.get().getId());
        }

        Map userInfo = restService.getUserInfo(APIURL, accessToken);
        Map profile = objectMapper.convertValue(userInfo.get("kakao_account"), Map.class);


        String uuid = UUID.randomUUID().toString();
        UserEntity user = UserEntity.builder()
                .id(uuid)
                .userName("test")
                .email(profile.get("email").toString())
                .createdAt(LocalDateTime.now())
                .authId(tokenInfo.get("id").toString())
                .build();

        userRepository.save(user);

        return ImmutableMap.of("existed", false, "id", uuid);
    }
}
