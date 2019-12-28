package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tag")
public class Tag extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(condition= SqlCondition.LIKE)
    private String tagName;

    private Integer articleId;
}
