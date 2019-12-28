package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_role")
public class UserRole extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer UserId;
    private Integer roleId;

}
