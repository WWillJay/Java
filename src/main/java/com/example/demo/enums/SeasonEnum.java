package com.example.demo.enums;

/**
 * 使用enum关键字创建枚举类
 */
public enum SeasonEnum implements info{
    /**
     * 1.提供当前枚举类对象，多个对象之间使用逗号隔开，结尾使用分号
     *      对象名称相当于构造器
     */
    SPRING("春天", "春暖花开", 1) {
        @Override
        public void show() {
            System.out.println("春天show");
        }
    },
    SUMMER("夏天", "夏日炎炎", 2),
    AUTUMN("秋天", "秋高气爽", 3){
        @Override
        public void show() {
            System.out.println("秋天show");
        }
    },
    WINTER("冬天", 4, "冰天雪地");

    /**
    * 2.声明类属性：private final修饰
    */
    /**
     * 季节名称
     */
    private final String seasonName;

    /**
     * 季节描述
     */
    private final String seasonDesc;

    private final int code;

    /**
     * 2.私有化构造器（对属性赋值）
     */
    SeasonEnum(String seasonName, String seasonDesc, int code){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
        this.code = code;
    }

    SeasonEnum(String seasonName, int code, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    @Override
    public void show() {
        System.out.println("这是一个季节:" + seasonName);
    }
}

/**
 * 测试类
 */
class SeasonEnumTest{
    public static void main(String[] args) {

        /**
         * xxEnum.对象名.toString(),返回指定对象名
         */
        System.out.println("----------xxEnum.对象名.toString()----------");
        SeasonEnum spring = SeasonEnum.SPRING;
        System.out.println("spring.toString():" + spring.toString() + ",  code: " + spring.getCode());

        /**
         * xxEnum.values()，返回枚举类中数组（所有对象）
         */
        System.out.println("----------xxEnum.values()----------");
        SeasonEnum[] values = SeasonEnum.values();
        for (SeasonEnum value : values) {
            System.out.println(value);
        }
        /**
         * xxEnum.valueOf(String name)，返回枚举类中名为name的对象，没有的话会报错 IllegalArgumentException
         */
        System.out.println("----------xxEnum.valueOf(String name)----------");
        SeasonEnum summer = SeasonEnum.valueOf("SUMMER");
        System.out.println(summer);

        /**
         * enum实现接口情况
         */
        System.out.println("----------enum实现接口情况----------");
        SeasonEnum.SPRING.show();
        SeasonEnum.SUMMER.show();
        SeasonEnum.AUTUMN.show();
        SeasonEnum.WINTER.show();
    }
}


interface info{
    void show();
}