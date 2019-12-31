package fun.imcoder.cloud.auth.controller;

import fun.imcoder.cloud.base.common.ResponseData;
import fun.imcoder.cloud.security.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import fun.imcoder.cloud.base.support.BaseController;
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
    public ResponseData<User> getUser(OAuth2Authentication authentication) {
        return ResponseData.success((User) authentication.getPrincipal());
    }

    @GetMapping("/client")
    public ResponseData<String> getClientId() {
        return ResponseData.success(SecurityUtil.getClientId());
    }

    @GetMapping("/test")
    public ResponseData<User> tsst() {
        return ResponseData.success(SecurityUtil.getUser());
    }

}
