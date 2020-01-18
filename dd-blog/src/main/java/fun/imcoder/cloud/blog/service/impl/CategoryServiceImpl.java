package fun.imcoder.cloud.blog.service.impl;

import fun.imcoder.cloud.base.support.BaseServiceImpl;
import fun.imcoder.cloud.blog.mapper.CategoryMapper;
import fun.imcoder.cloud.blog.model.Category;
import fun.imcoder.cloud.blog.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {

}
