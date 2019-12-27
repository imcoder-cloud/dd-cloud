package fun.imcoder.cloud.blog.controller;

import fun.imcoder.cloud.base.support.BaseController;
import fun.imcoder.cloud.blog.model.Article;
import fun.imcoder.cloud.blog.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<Article, ArticleService> {

}
