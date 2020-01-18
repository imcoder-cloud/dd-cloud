package fun.imcoder.cloud.security.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User extends BaseModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String password;
    private String username;
    private String mobile;
    private String email;

    @TableField(exist = false)
    private List<GrantedAuthority> authorities;
    @TableField(exist = false)
    private Integer articleCount;
    @TableField(exist = false)
    private Integer likesCount;
    @TableField(exist = false)
    private Integer viewsCount;

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
