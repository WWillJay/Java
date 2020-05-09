package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.UserCarBreakRuleDetail;

import java.util.*;

public class DemoTest {
    public static void main(String[] args) {
//        System.out.println(-15>>2);
//        System.out.println(1073741820 << 2);
//        List<String> list = new ArrayList<>();
//        list.add(new String("aaa"));
//        list.add(new String("bbb"));

        List<UserCarBreakRuleDetail> userCarBreakRuleDetailList = getUserCarBreakRuleDetailList("{\n" +
                "\t\"showapi_res_code\": 0,\n" +
                "\t\"showapi_res_error\": \"\",\n" +
                "\t\"showapi_res_body\": {\n" +
                "\t\t\"count\": 2,//违章的数量\n" +
                "\t\t\"carEngineCode\": \"366893\",//发送机编号\n" +
                "\t\t\"carCode\": \"037572\",//车架号\n" +
                "\t\t\"errorMsg\": \"查询成功!\",//错误提示\n" +
                "\t\t\"msg\": \"查询成功!\",\n" +
                "\t\t\"carType\": \"02\",\n" +
                "\t\t\"ret_code\": 0,//是否扣费\n" +
                "\t\t\"flag\": true,//查询是否成功\n" +
                "\t\t\"carNumber\": \"贵AH171R\",//车牌\n" +
                "\t\t\"errorCode\": 0,//错误代码\n" +
                "\t\t\"createDate\": 1476416425566,\n" +
                "\t\t\"records\": [//违法记录列表\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"time\": \"2016-04-28 18:36:32\",//违章时间\n" +
                "\t\t\t\t\"degree\": \"6\",//扣分\n" +
                "\t\t\t\t\"reason\": \"驾驶机动车违反道路交通信号灯通行的\",//罚款原因\n" +
                "\t\t\t\t\"address\": \"黔灵山路（贵阳一中人行横道）\",//违章地址\n" +
                "\t\t\t\t\"department\": \"\",//违章处理的部门\n" +
                "\t\t\t\t\"cityName\": \"\",//违章所在的城市,部分地区无此数据\n" +
                "\t\t\t\t\"money\": \"150\",//罚款金额\n" +
                "\t\t\t\t\"code\": \"\"//违章的代号\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"time\": \"2016-04-25 14:13:01\",//违章时间\n" +
                "\t\t\t\t\"degree\": \"0\",//扣分\n" +
                "\t\t\t\t\"reason\": \"机动车违反规定停放、临时停车且驾驶人不在现场或者虽在现场但驾驶人拒绝立即驶离，妨碍其它车辆、行人通行的\",//罚款原因\n" +
                "\t\t\t\t\"address\": \"新添大道南段与半边街交叉口20米\",//违章地址\n" +
                "\t\t\t\t\"department\": \"\",//违章处理的部门\n" +
                "\t\t\t\t\"cityName\": \"\",//违章所在的城市,部分地区无此数据\n" +
                "\t\t\t\t\"money\": \"150\",//罚款金额\n" +
                "\t\t\t\t\"code\": \"\"//违章的代号\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"createDateStr\": \"2016-10-14 11:40:25\"\n" +
                "\t}\n" +
                "}");
        System.out.println();

    }

    public static List<UserCarBreakRuleDetail> getUserCarBreakRuleDetailList(String resultContentJson){
        JSONObject jsonObject = JSON.parseObject(resultContentJson);
        JSONObject resBody = jsonObject.getJSONObject("showapi_res_body");
        JSONArray records = resBody.getJSONArray("records");
        return records.toJavaList(UserCarBreakRuleDetail.class);
    }


    public static Long getIntradayLeftSecond(){
        Date day = new Date();
        Date dayLatest = getDayLatest(day);
        return (dayLatest.getTime() - (day.getTime())) / 1000;
    }


    public static Date getDayEarliest(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDayLatest(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        // 分
        calendar.set(Calendar.MINUTE, 59);
        // 秒
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
