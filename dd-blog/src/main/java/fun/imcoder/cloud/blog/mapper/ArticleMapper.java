package fun.imcoder.cloud.blog.mapper;

import fun.imcoder.cloud.base.support.BaseDdMapper;
import fun.imcoder.cloud.blog.model.Article;

public interface ArticleMapper extends BaseDdMapper<Article> {

    Integer addViews(Article article);

}
