package fun.imcoder.cloud.auth.service.impl;

import fun.imcoder.cloud.auth.model.User;
import fun.imcoder.cloud.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userService.findByName(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_APP"));
        return user;
    }
}
