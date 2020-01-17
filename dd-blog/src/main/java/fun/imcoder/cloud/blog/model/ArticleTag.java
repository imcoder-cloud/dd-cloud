package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_tag")
public class ArticleTag extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer articleId;
    private Integer tagId;
    private Integer userId;
}
