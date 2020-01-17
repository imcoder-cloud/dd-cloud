package fun.imcoder.cloud.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.imcoder.cloud.blog.model.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> list(Tag tag);
}
