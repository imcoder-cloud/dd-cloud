package fun.imcoder.cloud.blog.controller;

import fun.imcoder.cloud.base.support.BaseController;
import fun.imcoder.cloud.blog.model.Role;
import fun.imcoder.cloud.blog.service.RoleService;
import fun.imcoder.cloud.security.model.User;
import fun.imcoder.cloud.security.utils.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Role, RoleService> {

    @GetMapping("/test")
    private User test() throws InvocationTargetException, IllegalAccessException {
        User user = SecurityUtil.getUser();
        return user;
    }

}
