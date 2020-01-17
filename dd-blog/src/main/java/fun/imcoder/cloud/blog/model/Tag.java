package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tag")
public class Tag extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer tagId;

    @TableField(condition= SqlCondition.LIKE)
    private String tagName;

    private Integer userId;

    @TableField(exist= false)
    private List<Integer> tagIds;
}
