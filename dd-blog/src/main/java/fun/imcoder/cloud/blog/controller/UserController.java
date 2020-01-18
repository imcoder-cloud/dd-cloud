package fun.imcoder.cloud.blog.controller;

import fun.imcoder.cloud.base.support.BaseController;
import fun.imcoder.cloud.blog.service.UserService;
import fun.imcoder.cloud.security.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {

}
