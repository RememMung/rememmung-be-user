package rememmung.be_user.factory;

import java.util.Map;
import rememmung.be_user.user.KakaoOAuth2UserInfo;
import rememmung.be_user.user.OAuth2Provider;
import rememmung.be_user.user.OAuth2UserInfo;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId,
                                                   String accessToken,
                                                   Map<String, Object> attributes) {
        if (OAuth2Provider.KAKAO.getRegistrationId().equals(registrationId))
            return new KakaoOAuth2UserInfo(accessToken, attributes);
        return null;
    }
}
