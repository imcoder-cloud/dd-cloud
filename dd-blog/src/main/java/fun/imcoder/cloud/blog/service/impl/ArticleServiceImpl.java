package fun.imcoder.cloud.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.blog.mapper.ArticleMapper;
import fun.imcoder.cloud.blog.model.Article;
import fun.imcoder.cloud.blog.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
