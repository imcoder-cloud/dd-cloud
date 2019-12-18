package fun.imcoder.cloud.auth.service.impl;

import fun.imcoder.cloud.auth.mapper.UserMapper;
import fun.imcoder.cloud.auth.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import fun.imcoder.cloud.base.support.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

    @Override
    public List<User> findAll() {
        log.info("第1次查询");
        List<User> list = super.findAll();
        log.info("第2次查询");
        List<User> list2 = super.findAll();
        log.info("查询结束");
        return list2;
    }
}
