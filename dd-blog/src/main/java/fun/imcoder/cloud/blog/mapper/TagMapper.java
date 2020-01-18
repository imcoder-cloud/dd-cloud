package fun.imcoder.cloud.blog.mapper;

import fun.imcoder.cloud.base.support.BaseDdMapper;
import fun.imcoder.cloud.blog.model.Tag;

import java.util.List;

public interface TagMapper extends BaseDdMapper<Tag> {
    List<Tag> list(Tag tag);
}
