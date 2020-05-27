package com.example.demo.stream;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        StreamModel model1 = new StreamModel("liki", 20);
        StreamModel model2 = new StreamModel("lbli", 11);
        StreamModel model3 = new StreamModel("lili", 21);
        StreamModel model4 = new StreamModel("lili", 44);
        StreamModel model5 = new StreamModel("ligi", 3);

        ArrayList<StreamModel> list = new ArrayList<>();
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        list.add(model5);

        List<String> collect = list.stream().map(StreamModel::getName).distinct().collect(Collectors.toList());


        System.out.println(collect);


    }
}

@Data
class StreamModel{
    String name;
    Integer age;
    public StreamModel(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}