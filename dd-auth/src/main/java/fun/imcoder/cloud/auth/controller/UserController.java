package fun.imcoder.cloud.auth.controller;

import fun.imcoder.cloud.auth.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import fun.imcoder.cloud.base.support.BaseController;
import fun.imcoder.cloud.base.vo.ResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User,UserService> {

    @Resource
    private UserService userService;

    @GetMapping("/info/{name}")
    public ResponseVO postAccessToken(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.findByName(name);
        return ResponseVO.success(user);
    }
}
