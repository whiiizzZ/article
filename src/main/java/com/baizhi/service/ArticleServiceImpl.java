package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import com.baizhi.esdao.ArticleESDAO;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleESDAO articleESDAO;
    @Autowired
    ArticleDAO articleDAO;
    TransportClient transportClient;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> findAllArticle(String aa, int page) throws IOException {
        return articleESDAO.findAllArticleByEs(aa, page);
    }

    @Override
    public void insertArticle(Article article) throws IOException {

        articleDAO.insertArticle(article);
        //往索引库里添加文档
        List<Article> allArticle = articleDAO.findAllArticle();
        for (Article articles : allArticle) {
            XContentBuilder xContentBuilder1 = null;
            xContentBuilder1 = XContentFactory.jsonBuilder();

            XContentBuilder xContentBuilder2 = null;

            xContentBuilder2 = xContentBuilder1.startObject()
                    .field("name", articles.getName())
                    .field("author", articles.getAuthor())
                    .field("type", articles.getType())
                    .field("content", articles.getContent())
                    .field("href", articles.getHref())
                    .endObject();
            transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.200"), 9300));
            //指定请求的索引类型和json
            transportClient.prepareIndex("article", "articles").setSource(xContentBuilder2).get();
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Article findArticleById(String id) {
        return articleDAO.findArticleById(id);
    }
}
