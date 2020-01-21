package fun.imcoder.cloud.blog.model;

import com.baomidou.mybatisplus.annotation.*;
import fun.imcoder.cloud.base.support.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article")
public class Article extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Integer articleId;

    @TableField(condition = SqlCondition.LIKE)
    private String title;
    private String content;
    private String html;
    private String summary;
    private Integer userId;
    private Integer status;
    private Integer original;
    private Integer views;
    private Integer likes;
    private Integer comments;
    private String cover;
    private Integer top;
    private Integer recommend;

    @TableField(exist = false)
    private List<Category> categoryList;
    @TableField(exist = false)
    private List<Tag> tagList;

}
