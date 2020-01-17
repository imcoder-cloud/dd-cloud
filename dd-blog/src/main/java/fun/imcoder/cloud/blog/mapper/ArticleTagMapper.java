package fun.imcoder.cloud.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.imcoder.cloud.blog.model.ArticleTag;

import java.util.List;

public interface ArticleTagMapper extends BaseMapper<ArticleTag> {
    Boolean insertBatch(List<ArticleTag> articleTagList);
}
