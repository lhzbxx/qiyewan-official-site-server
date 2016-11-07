package com.qiyewan.controller;

import com.github.javafaker.Faker;
import com.qiyewan.domain.Article;
import com.qiyewan.dto.ArticleDto;
import com.qiyewan.service.ArticleService;
import com.qiyewan.utils.ArticleGenerator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * 评论
 */

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public Page<Article> showList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                  @RequestParam String category) {
        return articleService.getArticlesByCategory(category, pageable);
    }

    @CrossOrigin
    @GetMapping("/articles/{id}")
    public ArticleDto show(@PathVariable Long id) {
        return articleService.findArticleNode(id);
    }

    /**
     * 测试后请删除
     */
    @GetMapping("/articles/fake")
    public String fake() {

        Faker faker = new Faker();
        Random rd = new Random();

        for (int i = 0; i < 50; i++) {
            Article article = new Article();
            article.setAuthor(faker.name().firstName());
            article.setCategory("cate_" + (rd.nextInt(4) + 1));
            article.setContent(faker.lorem().paragraph());
            article.setTitle(faker.book().title());
            article.setCreateAt(new Date());
            article.setViewers(faker.number().numberBetween(0, 3000));
            articleService.save(article);
        }
        return "faked";
    }


    //region 生成新闻json
    private String topCategory = ArticleGenerator.getPropertyValue("top.category");
    private String centerCategory1 = ArticleGenerator.getPropertyValue("center.category1");
    private String centerCategory2 = ArticleGenerator.getPropertyValue("center.category2");
    private String bottomCategory1 = ArticleGenerator.getPropertyValue("bottom.category1");
    private String bottomCategory2 = ArticleGenerator.getPropertyValue("bottom.category2");
    private String sideCategory1 = ArticleGenerator.getPropertyValue("side.category1");
    private String sideCategory2 = ArticleGenerator.getPropertyValue("side.category2");
    private String sideCategory3 = ArticleGenerator.getPropertyValue("side.category3");
    @GetMapping("/newsList")
    public String generateNewsList(){
        Page<Article> topNews = articleService.getArticlesByCategory(topCategory, new PageRequest(0, 5));
        Page<Article> centerNews1 = articleService.getArticlesByCategory(centerCategory1, new PageRequest(0, 6));
        Page<Article> centerNews2 = articleService.getArticlesByCategory(centerCategory2, new PageRequest(0, 6));
        Page<Article> bottomNews1 = articleService.getArticlesByCategory(bottomCategory1, new PageRequest(0, 6));
        Page<Article> bottomNews2 = articleService.getArticlesByCategory(bottomCategory2, new PageRequest(0, 6));
        Page<Article> sideNews1 = articleService.getArticlesByCategory(sideCategory1, new PageRequest(0, 3));
        Page<Article> sideNews2 = articleService.getArticlesByCategory(sideCategory2, new PageRequest(0, 2));
        Page<Article> sideNews3 = articleService.getArticlesByCategory(sideCategory3, new PageRequest(0, 2));

        JSONObject json = new JSONObject();
        json.put("topNews", ArticleGenerator.generateTopNews(topNews.getContent()));
        json.put("centerNewsList", ArticleGenerator.generateNewsList(centerNews1.getContent(), centerNews2.getContent()));
        json.put("bottomNewsList", ArticleGenerator.generateNewsList(bottomNews1.getContent(), bottomNews2.getContent()));
        JSONArray sideNewsJson = new JSONArray();
        sideNewsJson.put(ArticleGenerator.generateNews(sideNews1.getContent()));
        sideNewsJson.put(ArticleGenerator.generateNews(sideNews2.getContent()));
        sideNewsJson.put(ArticleGenerator.generateNews(sideNews3.getContent()));
        json.put("sideNewsList", sideNewsJson);

        return json.toString();
    }
    //endregion
}