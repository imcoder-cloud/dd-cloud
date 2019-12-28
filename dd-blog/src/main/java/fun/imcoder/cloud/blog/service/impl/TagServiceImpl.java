package fun.imcoder.cloud.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.blog.mapper.TagMapper;
import fun.imcoder.cloud.blog.model.Tag;
import fun.imcoder.cloud.blog.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
