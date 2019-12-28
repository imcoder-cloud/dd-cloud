package fun.imcoder.cloud.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.blog.mapper.ArticleCategoryMapper;
import fun.imcoder.cloud.blog.model.ArticleCategory;
import fun.imcoder.cloud.blog.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

}
