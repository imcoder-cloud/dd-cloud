package fun.imcoder.cloud.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.blog.mapper.CategoryMapper;
import fun.imcoder.cloud.blog.model.Category;
import fun.imcoder.cloud.blog.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
