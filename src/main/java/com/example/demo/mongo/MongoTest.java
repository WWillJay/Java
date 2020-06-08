package com.example.demo.mongo;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MongoTest {
    public static void main(String[] args) {

        method1();

    }

    public static void method(){
        List<Integer> smsTypeList = new ArrayList<>();
        smsTypeList.add(1);
        smsTypeList.add(2);
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("172.16.5.200", 27017), "yryc_dev");
        GroupOperation operation = Aggregation.group("mobile").count().as("total")
                // 发送状态（1未知，2失败，3成功，4拦截）
                .sum(AggregationSpELExpression.expressionOf("cond(status == 1, 1, 0)")).as("unknown")
                .sum(AggregationSpELExpression.expressionOf("cond(status == 2, 1, 0)")).as("fail")
                .sum(AggregationSpELExpression.expressionOf("cond(status == 3, 1, 0)")).as("success")
                .sum(AggregationSpELExpression.expressionOf("cond(status == 4, 1, 0)")).as("intercept")
                .sum("costPrice").as("costPrice")
                .last("carrier").as("providerType")
                .last("pCode").as("provinceCode");
        List<Long> typeIds = new ArrayList<>();
        typeIds.add(1L);
        typeIds.add(2L);
        JSONObject expressionJson = new JSONObject();
        for (Long typeId : typeIds) {
            String keyName = "type_" + typeId;
            operation = operation.sum(AggregationSpELExpression.expressionOf("cond(typeId == " + typeId + ", 1, 0)")).as(keyName);
            expressionJson.put(keyName, "$" + keyName);
        }
        System.out.println("expression: " + expressionJson.toString());
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("sendTime").lt(new Date(1590647223000L)).gt(new Date(1588055223000L))),
                operation,
                Aggregation.project("total", "unknown", "fail", "success", "intercept", "costPrice", "provinceCode", "providerType")
                        .and("_id").as("mobile").andExclude("_id")
                        .andExpression(expressionJson.toString()).as("typeMap"),
                Aggregation.sort(Sort.Direction.DESC, "total"),
                Aggregation.limit(50)
        );

        AggregationResults<UserSmsAnalyzeOVO> results = mongoTemplate.aggregate(agg, "sms_content", UserSmsAnalyzeOVO.class);
        List<UserSmsAnalyzeOVO> mapList = results.getMappedResults();
    }

    public static void method1(){
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("172.16.5.200", 27017), "yryc_dev");

        QueryPlatformMessage queryPlatformMessage = new QueryPlatformMessage();

//        queryPlatformMessage.setContent("10000");
        queryPlatformMessage.setTypeId(2L);

        List<MatchOperation> list = new ArrayList<>();
        Date endTime = new Date(1599647223000L);
        Date beginTime = new Date(1588055223000L);
        Integer pageNum = 1;
        Integer pageSize = 20;


        Criteria criteria = new Criteria();
        if (queryPlatformMessage.getTypeId() == 0L) {
            criteria.and("typeId").nin(1, 2);
        } else {
            criteria.and("typeId").is(queryPlatformMessage.getTypeId());
        }

        if (beginTime != null && endTime != null){
            criteria.and("sendTime").gte(beginTime).lte(endTime);
        }

        if (!StringUtils.isEmpty(queryPlatformMessage.getContent())) {
            criteria.and("content").regex(".*?" + queryPlatformMessage.getContent() + ".*");
        }

        if (!StringUtils.isEmpty(queryPlatformMessage.getMobile())) {
            criteria.and("mobile").regex(".*?" + queryPlatformMessage.getMobile() + ".*");
        }

        if (queryPlatformMessage.getChannelId() != null) {
            criteria.and("channelId").is(queryPlatformMessage.getChannelId());
        }

        if (queryPlatformMessage.getCarrier() != null) {
            criteria.and("carrier").is(queryPlatformMessage.getCarrier());
        }

        if (queryPlatformMessage.getStatus() != null) {
            criteria.and("status").is(queryPlatformMessage.getStatus());
        }

        Aggregation agg = Aggregation.newAggregation(Aggregation.match(criteria),
//                Aggregation.count().as("count"),
                Aggregation.skip(skipNum(pageNum, pageSize)),
                Aggregation.limit(pageSize)
        );

        AggregationResults<QueryPlatformMessageOVO> results = mongoTemplate.aggregate(agg, "sms_content", QueryPlatformMessageOVO.class);
        Long count = mongoTemplate.count(new Query(criteria), "sms_content");
        List<QueryPlatformMessageOVO> mapList = results.getMappedResults();
    }

    public static Long skipNum(Integer pageNum, Integer pageSize) {
        return pageNum > 1 ? Integer.toUnsignedLong((pageNum - 1) * pageSize) : 0;
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
    private Map<String, Integer> typeMap;
}

@Data
class QueryPlatformMessage{

    /**
     * 短信类型： 0-系统 1-营销 2-拉新
     */
    private Long typeId;

    /**
     * 手机号（接收手机号）
     */
    private String mobile;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 通道ID
     */
    private Long channelId;

    /**
     * 运营商（1移动，2联通，3电信）
     */
    private Integer carrier;

    /**
     * 发送状态（1未知(提交成功)，2失败，3成功，4拦截）
     */
    private Integer status;

}

@Data
class QueryPlatformMessageOVO{
    /**
     * 手机号（接收手机号）
     */
    private String mobile;
    /**
     * 短信类型ID
     */
    private Long typeId;
    /**
     * 省份code
     */
    private String pCode;
    /**
     * 城市code
     */
    private String cCode;
    /**
     * 运营商（1移动，2联通，3电信）
     */
    private Integer carrier;
    /**
     * 短信签名
     */
    private String signName;
    /**
     * 发送状态（1未知(提交成功)，2失败，3成功，4拦截）
     */
    private Integer status;
    /**
     * 回执状态（1成功，2失败）
     */
    private String cbStatus;
    /**
     * 错误代码
     */
    private String errCode;
    /**
     * 描述
     */
    private String msg;
    /**
     * 短信条数
     */
    private Integer smsNum;
    /**
     * 内容
     */
    private String content;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 回调间隔时间
     */
    private Long cbCostTime;
    /**
     * 通道ID
     */
    private Long channelId;

}