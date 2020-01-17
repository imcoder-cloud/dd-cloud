package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("category")
public class Category extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    @TableField(condition= SqlCondition.LIKE)
    private String categoryName;

    private Integer userId;

    @TableField(exist= false)
    private List<Integer> categoryIds;
}
