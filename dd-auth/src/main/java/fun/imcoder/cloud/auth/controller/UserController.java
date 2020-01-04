package fun.imcoder.cloud.auth.controller;

import fun.imcoder.cloud.base.common.ResponseData;
import fun.imcoder.cloud.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public ResponseData getUser(OAuth2Authentication authentication) {
        return ResponseData.success(authentication.getPrincipal());
    }

    @GetMapping("/client")
    public ResponseData<String> getClientId() {
        return ResponseData.success(SecurityUtil.getClientId());
    }

}
