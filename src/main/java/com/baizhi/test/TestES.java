package com.baizhi.test;

import com.baizhi.SpringBootES;
import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest(classes = SpringBootES.class)
@RunWith(SpringRunner.class)
public class TestES {
    @Autowired
    ArticleDAO articleDAO;
    TransportClient transportClient;

    @Test
    public void test1() throws IOException, ExecutionException, InterruptedException {
        transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.200"), 9300));
        //创建索引
        transportClient.admin().indices().prepareCreate("article").get();
        //创建类型指定映射
        XContentBuilder xContentBuilder = null;
        xContentBuilder = XContentFactory.jsonBuilder();
        xContentBuilder.startObject()
                .startObject("properties")
                .startObject("name")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("author")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("type")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("content")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("href")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .endObject().endObject();

        PutMappingRequest putMappingRequest = new PutMappingRequest("article").type("articles").source(xContentBuilder);

        transportClient.admin().indices().putMapping(putMappingRequest).get();

    }

    @Test
    public void test2() throws IOException {

        //往索引库里添加文档
        List<Article> allArticle = articleDAO.findAllArticle();
        for (Article article : allArticle) {
            XContentBuilder xContentBuilder1 = null;
            xContentBuilder1 = XContentFactory.jsonBuilder();

            XContentBuilder xContentBuilder2 = null;

            xContentBuilder2 = xContentBuilder1.startObject()
                    .field("name", article.getName())
                    .field("author", article.getAuthor())
                    .field("type", article.getType())
                    .field("content", article.getContent())
                    .field("href", article.getHref())
                    .endObject();
            transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.200"), 9300));


            //指定请求的索引类型和json
            transportClient.prepareIndex("article", "articles").setSource(xContentBuilder2).get();
        }
    }

}
