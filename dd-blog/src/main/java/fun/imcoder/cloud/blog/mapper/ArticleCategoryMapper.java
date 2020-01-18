package fun.imcoder.cloud.blog.mapper;

import fun.imcoder.cloud.base.support.BaseDdMapper;
import fun.imcoder.cloud.blog.model.ArticleCategory;

import java.util.List;

public interface ArticleCategoryMapper extends BaseDdMapper<ArticleCategory> {
    Boolean insertBatch(List<ArticleCategory> articleCategoryList);
}
