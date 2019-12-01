package com.baizhi.dao;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleDAO {
    //查询所有
    public List<Article> findAllArticle();

    //添加
    public void insertArticle(Article article);

    //通过id查询
    public Article findArticleById(String id);
}
