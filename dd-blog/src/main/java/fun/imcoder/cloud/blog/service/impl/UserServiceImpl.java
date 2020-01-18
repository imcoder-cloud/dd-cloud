package fun.imcoder.cloud.blog.service.impl;

import fun.imcoder.cloud.base.support.BaseServiceImpl;
import fun.imcoder.cloud.blog.mapper.UserMapper;
import fun.imcoder.cloud.blog.service.UserService;
import fun.imcoder.cloud.security.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getById(Serializable id) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", id);
        List<User> list = this.baseMapper.customList(param);
        return list.get(0);
    }
}
