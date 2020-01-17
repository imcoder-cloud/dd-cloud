package fun.imcoder.cloud.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.imcoder.cloud.blog.model.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> list(Category category);
}
