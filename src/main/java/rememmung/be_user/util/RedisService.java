package rememmung.be_user.util;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String saveSession(String userId) {
        UUID uuid = UUID.randomUUID();
        redisTemplate.opsForValue().set(uuid.toString(), userId);
        return uuid.toString();
    }

    public String getUserIdByToken(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    public void deleteSession(String token) {
        redisTemplate.delete(token);
    }
}
