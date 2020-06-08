package com.example.demo.mongo;

import com.mongodb.MongoClient;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

import java.util.List;

public class MongoTest3 {

    public static void main(String[] args) {
//        getDayAnalyzeRecord(12L, null);
        getAreaAnalyzeRecord(12L);
    }

    public static List<DayAnalyzeRecord> getDayAnalyzeRecord(Long merchantShortUrlId, String pCode) {
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("172.16.5.200", 27017), "yryc_dev");
        Criteria criteria = Criteria.where("merchantShortUrlId").is(merchantShortUrlId);
        if (!StringUtils.isEmpty(pCode)) {
            criteria.and("pCode").is(pCode);
        }
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.project("pCode", "isDownload", "isRegistered")
                        .and(DateOperators.DateToString.dateOf("clickTime").toString("%Y-%m-%d").withTimezone(DateOperators.Timezone.valueOf("+08"))).as("dateStr"),
                Aggregation.group("dateStr")
                        .count().as("dayClickCount")
                        .sum("isDownload").as("downloadNum")
                        .sum("isRegistered").as("registeredNum"),
                Aggregation.project("_id", "dayClickCount", "downloadNum", "registeredNum").and("_id").as("dateStr").andExclude("_id")
        );
        AggregationResults<DayAnalyzeRecord> aggregate = mongoTemplate.aggregate(agg, "sms_merchant_short_url_record", DayAnalyzeRecord.class);

        return aggregate.getMappedResults();
    }

    public static List<AreaAnalyzeRecord> getAreaAnalyzeRecord(Long merchantShortUrlId) {
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("172.16.5.200", 27017), "yryc_dev");

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("merchantShortUrlId").is(merchantShortUrlId)),
                Aggregation.project("pCode", "isDownload", "isRegistered", "pName"),
                Aggregation.group("pCode", "pName")
                        .count().as("areaClickCount")
                        .sum("isDownload").as("downloadNum")
                        .sum("isRegistered").as("registeredNum"),
                Aggregation.project("_id", "areaClickCount", "downloadNum", "registeredNum", "pName").and("_id").as("pCode").andExclude("_id")
        );

        AggregationResults<AreaAnalyzeRecord> aggregate = mongoTemplate.aggregate(agg, "sms_merchant_short_url_record", AreaAnalyzeRecord.class);

        return aggregate.getMappedResults();
    }
}

@Data
class DayAnalyzeRecord {

    private String dateStr;

    private Long dayClickCount;

    private Long downloadNum;

    private Long registeredNum;
}

@Data
class AreaAnalyzeRecord {

    private String pCode;

    private String pName;

    private Long areaClickCount;

    private Long downloadNum;

    private Long registeredNum;
}