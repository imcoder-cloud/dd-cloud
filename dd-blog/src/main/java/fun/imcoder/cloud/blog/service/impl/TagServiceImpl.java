package fun.imcoder.cloud.blog.service.impl;

import fun.imcoder.cloud.base.support.BaseServiceImpl;
import fun.imcoder.cloud.blog.mapper.TagMapper;
import fun.imcoder.cloud.blog.model.Tag;
import fun.imcoder.cloud.blog.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends BaseServiceImpl<TagMapper, Tag> implements TagService {

}
