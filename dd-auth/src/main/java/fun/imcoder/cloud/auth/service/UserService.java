package fun.imcoder.cloud.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.imcoder.cloud.security.model.User;

public interface UserService extends IService<User> {
    User findByName(String name);
}
