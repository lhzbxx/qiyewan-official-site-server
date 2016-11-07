package com.qiyewan.utils;

import com.qiyewan.domain.Article;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by atom on 2016/11/7.
 */
public class ArticleGenerator {
    private static final String PROP_FILE = "newslist.properties";
    private ArticleGenerator(){}

    public static JSONObject generateTopNews(List<Article> articles){
        JSONObject topNewsJson = generateNews(articles);
        // put scroll side image urls
        JSONArray urlsJson = new JSONArray();
        urlsJson.put("");
        topNewsJson.put("imageURLs", urlsJson);

        return topNewsJson;
    }

    /**
     * 生成左右成对新闻列表
     * @param articleList1
     * @param articleList2
     * @return
     */
    public static JSONArray generateNewsList(List<Article> articleList1, List<Article> articleList2){
        JSONArray centerNewsListJson = new JSONArray();
        centerNewsListJson.put(generateNews(articleList1));
        centerNewsListJson.put(generateNews(articleList2));
        return centerNewsListJson;
    }

    public static JSONObject generateNews(List<Article> articles){
        JSONObject jsonObj = new JSONObject();
        Article _1stArticle = articles.get(0);
        jsonObj.put("id", _1stArticle.getId());
        jsonObj.put("category", _1stArticle.getCategory());
        jsonObj.put("title", _1stArticle.getTitle());
        jsonObj.put("cover", _1stArticle.getCover());

        // put articles exclude 1st article
        JSONArray articlesJson = new JSONArray();
        for (int i = 1; i < articles.size(); i++) {
            Article a = articles.get(i);
            JSONObject o = new JSONObject();
            o.put("id", a.getId());
            o.put("title", a.getTitle());
            Date createAt = a.getCreateAt();
            o.put("year_month", createAt.getYear() + "/" + createAt.getMonth());
            o.put("day", createAt.getDay());
            articlesJson.put(o);
        }
        jsonObj.put("articles", articlesJson);

        return jsonObj;
    }

    public static String getPropertyValue(String key) {
        Properties properties = new Properties();
        try {
            properties.load(ArticleGenerator.class.getClassLoader().getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            System.err.println("Could not find " + PROP_FILE);
        }
        return (String) properties.get(key);
    }
}
