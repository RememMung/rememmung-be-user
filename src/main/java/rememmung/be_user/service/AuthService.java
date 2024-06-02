package rememmung.be_user.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rememmung.be_user.util.RestService;

@Service
@RequiredArgsConstructor
public class AuthService implements UserService{

    private final RestService restService;
    @Override
    public boolean validateToken(String acceessToken, String APIURI) {
        try {
            Map res = restService.getRequest(APIURI, acceessToken);
//            System.out.println(res.toString());
            return res.get("statusCode").equals(200);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
