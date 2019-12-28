package fun.imcoder.cloud.blog.controller;

import fun.imcoder.cloud.base.support.BaseController;
import fun.imcoder.cloud.blog.model.Category;
import fun.imcoder.cloud.blog.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<Category, CategoryService> {

}
