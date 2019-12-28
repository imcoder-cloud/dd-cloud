package fun.imcoder.cloud.blog.controller;

import fun.imcoder.cloud.base.support.BaseController;
import fun.imcoder.cloud.blog.model.ArticleCategory;
import fun.imcoder.cloud.blog.service.ArticleCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article-category")
public class ArticleCategoryController extends BaseController<ArticleCategory, ArticleCategoryService> {

}
