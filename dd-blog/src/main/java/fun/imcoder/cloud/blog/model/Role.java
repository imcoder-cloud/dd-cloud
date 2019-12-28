package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role")
public class Role extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer roleId;

    @TableField(condition= SqlCondition.LIKE)
    private String roleName;
}
