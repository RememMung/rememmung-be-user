package rememmung.be_user.handler;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

public class OAuth2AuthenticationSuccessHandler implements OAuth2AuthorizationSuccessHandler {
    @Override
    public void onAuthorizationSuccess(OAuth2AuthorizedClient authorizedClient, Authentication principal,
                                       Map<String, Object> attributes) {

    }
    // 성공시 비즈니스 로직
}
