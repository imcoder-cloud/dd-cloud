package fun.imcoder.cloud.blog.controller;

import fun.imcoder.cloud.base.support.BaseController;
import fun.imcoder.cloud.blog.model.ArticleTag;
import fun.imcoder.cloud.blog.service.ArticleTagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article-tag")
public class ArticleTagController extends BaseController<ArticleTag, ArticleTagService> {

}
