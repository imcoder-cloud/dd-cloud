package fun.imcoder.cloud.blog.service;

import fun.imcoder.cloud.base.support.BaseService;
import fun.imcoder.cloud.blog.model.Article;

public interface ArticleService extends BaseService<Article> {

    Integer addViews(Article article);

}
