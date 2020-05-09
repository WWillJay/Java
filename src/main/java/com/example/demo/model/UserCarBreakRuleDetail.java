package com.example.demo.model;

import lombok.Data;

@Data
public class UserCarBreakRuleDetail {

    /**
     * 违章时间
     */
    private String time;

    /**
     * 扣分
     */
    private String degree;

    /**
     * 罚款原因
     */
    private String reason;

    /**
     * 违章地址
     */
    private String address;

    /**
     * 违章处理的部门
     */
    private String department;

    /**
     * 违章所在的城市,部分地区无此数据
     */
    private String cityName;

    /**
     * 罚款金额
     */
    private String money;

    /**
     * 违章的代号
     */
    private String code;
}
