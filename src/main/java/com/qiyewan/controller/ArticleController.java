package com.qiyewan.controller;

import com.qiyewan.domain.Article;
import com.qiyewan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
