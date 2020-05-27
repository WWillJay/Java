package com.example.demo.mongo;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.AggregationSpELExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoTest {
    public static void main(String[] args) {
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("172.16.5.200", 27017), "yryc_dev");
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("mobile").count().as("total")
                        // 发送状态（1未知，2失败，3成功，4拦截）
                        .sum(AggregationSpELExpression.expressionOf("cond(providerType == 1, 1, 0)")).as("unknown")
                        .sum(AggregationSpELExpression.expressionOf("cond(providerType == 2, 1, 0)")).as("fail")
                        .sum(AggregationSpELExpression.expressionOf("cond(providerType == 3, 1, 0)")).as("success")
                        .sum(AggregationSpELExpression.expressionOf("cond(providerType == 4, 1, 0)")).as("intercept")
                        .sum("costPrice").as("costPrice")
                        .last("providerType").as("providerType")
                        .last("provinceCode").as("provinceCode"),
                Aggregation.project( "total", "unknown", "fail", "success", "intercept", "costPrice", "provinceCode", "providerType").and("_id").as("mobile").andExclude("_id"),
                Aggregation.sort(Sort.DEFAULT_DIRECTION, "total"),
                Aggregation.limit(5)
        );
        AggregationResults<Map> results = mongoTemplate.aggregate(agg, "sms_content", Map.class);
        List<Map> mapList = results.getMappedResults();
        ArrayList<UserSmsAnalyzeOVO> list = new ArrayList<>();
        for (Map map : mapList) {
            UserSmsAnalyzeOVO userSmsAnalyzeOVO = JSON.parseObject(JSON.toJSONString(map), UserSmsAnalyzeOVO.class);
            list.add(userSmsAnalyzeOVO);
            System.out.println(userSmsAnalyzeOVO);
        }
    }
}

@Data
class UserSmsAnalyzeOVO {
    private String mobile;
    private String provinceCode;
    private Integer providerType;
    private Integer total;
    private Integer unknown;
    private Integer fail;
    private Integer success;
    private Integer intercept;
    private Integer costPrice;
}
