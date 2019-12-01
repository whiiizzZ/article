package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @ResponseBody
    @RequestMapping("findArticle")
    public List<Article> findArticle(String aa, int page) throws IOException, ExecutionException, InterruptedException {
        List<Article> allArticle = articleService.findAllArticle(aa, page);
        System.out.println(allArticle);
        return allArticle;
    }

    @RequestMapping("insertArticle")
    public String insertArticle(Article article, String name, String author, String type, String content) throws IOException {
        String id = UUID.randomUUID().toString();
        String href = "http://localhost:8989/article/article/findArticleById?id=" + id;
        article.setId(id);
        article.setName(name);
        article.setAuthor(author);
        article.setType(type);
        article.setContent(content);
        article.setHref(href);
        articleService.insertArticle(article);
        return "findArticle";
    }

    @RequestMapping("findArticleById")
    public String findArticleById(String id, HttpServletRequest request) {
        Article article = articleService.findArticleById(id);
        System.out.println(article);
        request.setAttribute("article", article);
        return "article";
    }

}
