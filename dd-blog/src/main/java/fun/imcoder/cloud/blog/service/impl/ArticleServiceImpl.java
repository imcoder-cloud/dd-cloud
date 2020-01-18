package fun.imcoder.cloud.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.imcoder.cloud.base.support.BaseServiceImpl;
import fun.imcoder.cloud.blog.mapper.*;
import fun.imcoder.cloud.blog.model.*;
import fun.imcoder.cloud.blog.service.ArticleService;
import fun.imcoder.cloud.security.model.User;
import fun.imcoder.cloud.security.utils.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private ArticleCategoryMapper articleCategoryMapper;
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Article getById(Serializable id) {
        Article article = articleMapper.selectById(id);
        Map<String, Object> param = new HashMap<>();
        param.put("article_id", article.getArticleId());
        List<ArticleCategory> articleCategoryList = articleCategoryMapper.selectByMap(param);
        List<Integer> categoryIds = articleCategoryList.stream().map(ArticleCategory::getCategoryId).collect(Collectors.toList());
        List<ArticleTag> articleTagList = articleTagMapper.selectByMap(param);
        List<Integer> tagIds = articleTagList.stream().map(ArticleTag::getTagId).collect(Collectors.toList());
        if (!categoryIds.isEmpty()) {
            Category category = new Category();
            category.setCategoryIds(categoryIds);
            List<Category> categoryList = categoryMapper.customList(category);
            article.setCategoryList(categoryList);
        }
        if (!tagIds.isEmpty()) {
            Tag tag = new Tag();
            tag.setTagIds(tagIds);
            List<Tag> tagList = tagMapper.customList(tag);
            article.setTagList(tagList);
        }
        return article;
    }

    @Override
    public boolean saveBatch(Collection<Article> entityList, int batchSize) {
        try {
            User user = SecurityUtil.getUser();
            Integer userId = user.getUserId();
            entityList.forEach(o -> {
                o.setUserId(userId);
                articleMapper.insert(o);
                insertCategoryTag(o);
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateBatchById(Collection<Article> entityList, int batchSize) {
        entityList.forEach(o -> {
            articleMapper.updateById(o);
            Map<String, Object> param = new HashMap<>();
            param.put("article_id", o.getArticleId());
            // 删除之前的记录
            articleTagMapper.deleteByMap(param);
            articleCategoryMapper.deleteByMap(param);
            insertCategoryTag(o);
        });
        return true;
    }

    private void insertCategoryTag(Article article) {
        User user = null;
        try {
            user = SecurityUtil.getUser();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Integer userId = user.getUserId();
        List<Tag> tagList = article.getTagList();
        List<ArticleTag> articleTagList = new ArrayList<>();
        Integer articleId = article.getArticleId();
        tagList.forEach(tag -> {
            ArticleTag at = new ArticleTag();
            at.setUserId(userId);
            at.setArticleId(articleId);
            at.setTagId(tag.getTagId());
            articleTagList.add(at);
        });
        articleTagMapper.insertBatch(articleTagList);
        List<Category> categoryList = article.getCategoryList();
        List<ArticleCategory> articleCategoryList = new ArrayList<>();
        categoryList.forEach(category -> {
            ArticleCategory ac = new ArticleCategory();
            ac.setUserId(userId);
            ac.setArticleId(articleId);
            ac.setCategoryId(category.getCategoryId());
            articleCategoryList.add(ac);
        });
        articleCategoryMapper.insertBatch(articleCategoryList);
    }

}
