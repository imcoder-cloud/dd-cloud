package fun.imcoder.cloud.auth.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/oauth")
@Slf4j
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

//    @PostMapping("/token")
//    public ResponseVO postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        return ResponseVO.success(tokenEndpoint.postAccessToken(principal, parameters).getBody());
//    }

    @GetMapping("/me")
    public Principal getUser(Principal user){
        log.info("进入获取用户信息方法");
        log.info(JSON.toJSONString(user));
        return user;
    }
}
