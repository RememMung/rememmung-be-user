package rememmung.be_user.service;

import lombok.Data;

@Data
public class UserInfoByToken {
    private Long id;
    private Integer expires_in;
    private Integer app_id;
}
