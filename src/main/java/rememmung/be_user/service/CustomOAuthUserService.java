package rememmung.be_user.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;
import rememmung.be_user.factory.OAuth2UserInfoFactory;
import rememmung.be_user.user.OAuth2UserInfo;
import rememmung.be_user.user.OAuth2UserPrincipal;

public class CustomOAuthUserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) throws Exception {

        String registrationId = userRequest.getClientRegistration()
                .getRegistrationId();

        String accessToken = userRequest.getAccessToken().getTokenValue();

        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId,
                accessToken,
                oAuth2User.getAttributes());

        // OAuth2UserInfo field value validation
        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new Exception("Email not found from OAuth2 provider");
        }

        return new OAuth2UserPrincipal(oAuth2UserInfo);
    }

}
