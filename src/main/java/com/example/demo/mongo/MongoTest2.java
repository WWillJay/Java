package com.example.demo.mongo;

import com.mongodb.MongoClient;
import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.AggregationSpELExpression;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MongoTest2 {
    public static void main(String[] args) {
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("172.16.5.200", 27017), "yryc_dev");
        double ceil = Math.ceil((double) 1 / (double)3);

//        Aggregation agg = Aggregation.newAggregation(Aggregation.match(
//                Criteria.where("merchantShortUrlId").is(12L)
//                        .and("sendTime").gte(getDayEarliest(new Date())).lte(new Date())),
////                Aggregation.count().as("pv"),
//                Aggregation.group("userSign").first("userSign").as("uv")
//        );


        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("sendTime").lt(new Date()).gt(getDay(new Date(), 30)).and("merchantShortUrlId").is(12L)),
                Aggregation.project("userSign", "visitSecond")
                        .and(DateOperators.DateToString.dateOf("sendTime").toString("%Y-%m-%d").withTimezone(DateOperators.Timezone.valueOf("+08"))).as("dateStr"),
                Aggregation.group("dateStr", "userSign")
                        .count().as("dayCount")
                        .sum("visitSecond").as("dayTotalSecond"),
                Aggregation.group("dateStr")
                        .count().as("uv")
                        .sum("dayCount").as("pv")
                        .sum("dayTotalSecond").as("totalSecond"),
                Aggregation.project("_id", "uv", "pv", "totalSecond").and("_id").as("dateStr").andExclude("_id")
        );

        AggregationResults<RecordDetail> aggregate = mongoTemplate.aggregate(agg, "sms_merchant_short_url_record", RecordDetail.class);
        List<RecordDetail> mappedResults = aggregate.getMappedResults();
        for (RecordDetail mappedResult : mappedResults) {
            System.err.println(mappedResult);
        }


        Aggregation agg2 = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("sendTime").lt(new Date()).gt(getDay(new Date(), 30)).and("merchantShortUrlId").is(12L)),
                Aggregation.project("ip")
                        .and(DateOperators.DateToString.dateOf("sendTime").toString("%Y-%m-%d").withTimezone(DateOperators.Timezone.valueOf("+08"))).as("dateStr"),
                Aggregation.group("dateStr", "ip"),
                Aggregation.group("dateStr").count().as("ipNum"),
                Aggregation.project("_id", "ipNum").and("_id").as("dateStr").andExclude("_id")
        );

        AggregationResults<IpRecordDetail> aggregate2 = mongoTemplate.aggregate(agg2, "sms_merchant_short_url_record", IpRecordDetail.class);
        List<IpRecordDetail> mappedResults2 = aggregate2.getMappedResults();
        for (IpRecordDetail mappedResult : mappedResults2) {
            System.err.println(mappedResult);
        }

    }

    public static Date getDay(Date date, Integer day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DATE, -day);
        return calendar.getTime();
    }

}
@Data
class RecordDetail {

    /**
     * 日期
     */
    private String dateStr;

    /**
     * 用户数(UV)
     */
    private Integer uv;

    /**
     * 浏览量(PV)
     */
    private Integer pv;

    private Long totalSecond;
}

@Data
class IpRecordDetail {

    /**
     * 日期
     */
    private String dateStr;

    /**
     * 用户数(UV)
     */
    private Integer ipNum;

}