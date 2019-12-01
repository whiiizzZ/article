package com.baizhi.test;

import com.baizhi.SpringBootES;
import com.baizhi.entity.Article;
import com.baizhi.esdao.ArticleESDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = SpringBootES.class)
@RunWith(SpringRunner.class)
public class TestArticleESDAO {
    @Autowired
    ArticleESDAO articleESDAO;

    @Test
    public void test1() throws IOException {
        String aa = "杜甫";
        int page = 0;
        List<Article> allArticleByEs = articleESDAO.findAllArticleByEs(aa, page);
        for (Article allArticleByE : allArticleByEs) {
            System.out.println(allArticleByE);
        }

    }
}
