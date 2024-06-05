package rememmung.be_user.service;

import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rememmung.be_user.util.RestService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestService restService;

    public Map getTokenInfo(String acceessToken, String APIURI) {
        try{
            return restService.getRequest(APIURI, acceessToken);
        }catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }
}
