package fun.imcoder.cloud.blog.mapper;

import fun.imcoder.cloud.base.support.BaseDdMapper;
import fun.imcoder.cloud.blog.model.ArticleTag;

import java.util.List;

public interface ArticleTagMapper extends BaseDdMapper<ArticleTag> {
    Boolean insertBatch(List<ArticleTag> articleTagList);
}
