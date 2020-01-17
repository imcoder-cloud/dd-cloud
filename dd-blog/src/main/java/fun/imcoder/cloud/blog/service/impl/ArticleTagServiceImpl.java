package fun.imcoder.cloud.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.blog.mapper.ArticleTagMapper;
import fun.imcoder.cloud.blog.model.ArticleTag;
import fun.imcoder.cloud.blog.service.ArticleTagService;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
