package fun.imcoder.cloud.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.imcoder.cloud.auth.model.User;

public interface UserService extends IService<User> {
    User findByName(String name);
}
