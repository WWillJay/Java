package com.example.demo.generic.dao;

public class Dao<T> {

    void insert(T t){

    }

    void delete(T t){

    }

    void update(T t){

    }

    T selectOne(T t){
        return t;
    }

    <E> E getCount(){
        return null;
    }
}
