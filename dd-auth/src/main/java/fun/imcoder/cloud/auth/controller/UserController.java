package fun.imcoder.cloud.auth.controller;

import fun.imcoder.cloud.auth.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import fun.imcoder.cloud.base.support.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User,UserService> {

}
