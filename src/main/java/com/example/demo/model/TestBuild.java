package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestBuild {
    public String name;

    public Integer id;
}
