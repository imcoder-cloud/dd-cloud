package fun.imcoder.cloud.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.imcoder.cloud.blog.model.ArticleCategory;

import java.util.List;

public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {
    Boolean insertBatch(List<ArticleCategory> articleCategoryList);
}
