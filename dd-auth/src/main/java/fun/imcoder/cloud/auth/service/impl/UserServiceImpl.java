package fun.imcoder.cloud.auth.service.impl;

import fun.imcoder.cloud.auth.mapper.UserMapper;
import fun.imcoder.cloud.auth.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import fun.imcoder.cloud.base.support.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

}
