package com.example.demo.enums;

import lombok.Data;

/**
 * 自定义枚举类
 */
@Data
public class SeasonNoEnum {

    /**
     * 1.声明类属性：private final修饰
     */
    /**
     * 季节名称
     */
    private final String seasonName;

    /**
     * 季节描述
     */
    private final String seasonDesc;

    /**
     * 2.私有化构造器（对属性赋值）
     */
    private SeasonNoEnum (String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    /**
     * 提供当前枚举类的多个对象：public static final修饰（静态常量）
     * 内部调用构造器，生成对外开发的有限个数且确定的对象
     */
    public static final SeasonNoEnum SPRING = new SeasonNoEnum("春天", "春暖花开");
    public static final SeasonNoEnum SUMMER = new SeasonNoEnum("夏天", "夏日炎炎");
    public static final SeasonNoEnum AUTUMN = new SeasonNoEnum("秋天", "秋高气爽");
    public static final SeasonNoEnum WINTER = new SeasonNoEnum("冬天", "冰天雪地");
}

/**
 * 测试类
 */
class SeasonNoEnumTest {
    public static void main(String[] args) {
        SeasonNoEnum spring = SeasonNoEnum.SPRING;
        System.out.println(spring.getSeasonName() + ":" + spring.getSeasonDesc());
    }
}