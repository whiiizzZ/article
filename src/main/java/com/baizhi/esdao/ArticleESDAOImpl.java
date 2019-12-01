package com.baizhi.esdao;

import com.baizhi.entity.Article;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleESDAOImpl implements ArticleESDAO {
    TransportClient transportClient;

    @Override
    public List<Article> findAllArticleByEs(String aa, int page) throws IOException {
        transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.5.200"), 9300));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.requireFieldMatch(false).preTags("<font color='pink'>").postTags("</font>").field("*");
        //搜索
        QueryStringQueryBuilder field = QueryBuilders.queryStringQuery(aa)
                .analyzer("ik_max_word")
                .field("name")
                .field("content");
        SearchResponse searchResponse = transportClient.prepareSearch("article")
                .setTypes("articles")
                .setQuery(field)
                .highlighter(highlightBuilder)
                .setFrom(page)
                .setSize(1)
                .get();


        SearchHit[] hits = searchResponse.getHits().getHits();
        List list = new ArrayList();

        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            for (String key : highlightFields.keySet()) {
                sourceAsMap.put(key, highlightFields.get(key).getFragments()[0].toString());
            }
            list.add(sourceAsMap);
        }
        ;
        return list;
    }


}
