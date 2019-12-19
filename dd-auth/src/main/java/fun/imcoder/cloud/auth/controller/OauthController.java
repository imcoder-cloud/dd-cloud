package fun.imcoder.cloud.auth.controller;

import fun.imcoder.cloud.base.common.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
@Slf4j
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public ResponseData postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return ResponseData.success(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }


}
