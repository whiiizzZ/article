package com.baizhi.service;

import com.baizhi.entity.Article;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ArticleService {
    public List<Article> findAllArticle(String aa, int page) throws InterruptedException, ExecutionException, IOException;

    //添加
    public void insertArticle(Article article) throws IOException;

    //通过id查询
    public Article findArticleById(String id);
}
