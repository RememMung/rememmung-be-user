package rememmung.be_user.handler;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import rememmung.be_user.entity.UserEntity;
import rememmung.be_user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserValidSuccessHandler {
    private final UserRepository userRepository;
    public Object checkUser(Long id) {
        if (userRepository.existsById(id)) {
            // id가 있으면 UserInfoByToken에 exist : true로 보내주고
            return userRepository.findById(id);
        } else {
            // id가 없으면 DB에 저장하고 return.
        }
        return null;
    }
}
