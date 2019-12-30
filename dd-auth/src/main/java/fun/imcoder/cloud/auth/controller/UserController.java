package fun.imcoder.cloud.auth.controller;

import fun.imcoder.cloud.security.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import fun.imcoder.cloud.base.support.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User,UserService> {
    @GetMapping("/info")
    public User getUser(OAuth2Authentication authentication){
        log.info("进入获取用户信息方法");
        return (User) authentication.getPrincipal();
    }
}
