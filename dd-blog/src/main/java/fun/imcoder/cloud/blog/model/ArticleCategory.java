package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_category")
public class ArticleCategory extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer articleId;
    private Integer categoryId;
}
