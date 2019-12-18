package fun.imcoder.cloud.auth.model;

import com.baomidou.mybatisplus.annotation.TableField;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    private String password;
    private String username;
    private String email;
    @TableField(exist = false)
    private List<GrantedAuthority> authorities;

    //权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    //获取密码
    @Override
    public String getPassword() {
        return this.password;
    }

    //获取用户名
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
