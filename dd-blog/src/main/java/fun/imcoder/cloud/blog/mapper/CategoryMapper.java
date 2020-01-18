package fun.imcoder.cloud.blog.mapper;

import fun.imcoder.cloud.base.support.BaseDdMapper;
import fun.imcoder.cloud.blog.model.Category;

import java.util.List;

public interface CategoryMapper extends BaseDdMapper<Category> {
    List<Category> list(Category category);
}
