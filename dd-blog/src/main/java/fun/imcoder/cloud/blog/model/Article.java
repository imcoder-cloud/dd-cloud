package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article")
public class Article extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer articleId;

    @TableField(condition= SqlCondition.LIKE)
    private String articleName;
    private String content;
    private String html;
    private String summary;
    private Integer status;
    private Integer original;
    private Integer view;
    private Integer love;
    private Integer comment;
    private String cover;
    private Integer top;
    private Integer recommend;
}
