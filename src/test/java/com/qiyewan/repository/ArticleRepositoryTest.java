package com.qiyewan.repository;

import com.github.javafaker.Faker;
import com.qiyewan.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

/**
 * Created by atom on 2016/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void fakeArticles(){

    }
}