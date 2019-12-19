package fun.imcoder.cloud.auth.controller;

import com.alibaba.fastjson.JSON;
import fun.imcoder.cloud.auth.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import fun.imcoder.cloud.base.support.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User,UserService> {
    @GetMapping("/info")
    public Principal getUser(Principal user){
        log.info("进入获取用户信息方法");
        log.info(JSON.toJSONString(user));
        return user;
    }
}
