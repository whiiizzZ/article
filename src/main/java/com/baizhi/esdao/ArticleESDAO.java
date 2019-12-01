package com.baizhi.esdao;

import com.baizhi.entity.Article;

import java.io.IOException;
import java.util.List;

public interface ArticleESDAO {
    public List<Article> findAllArticleByEs(String aa, int page) throws IOException;
}
