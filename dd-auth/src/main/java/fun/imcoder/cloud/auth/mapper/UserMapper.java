package fun.imcoder.cloud.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.imcoder.cloud.security.model.User;

public interface UserMapper extends BaseMapper<User> {
    User findByName(String name);
}
