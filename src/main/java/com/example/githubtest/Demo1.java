package com.example.githubtest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Demo1 {
    public static void main(String[] args) {
        List<PojoTest> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = Maps.newHashMap();


        List<String> list1 = Lists.newArrayList();
        list1.forEach(new Consumer<Object>() {
            @Override
            public void accept(Object s) {
                System.out.println(s);
            }
        });

        Consumer<String> consumer = new Consumer<String>(){

            /**
             * Performs this operation on the given argument.
             *
             * @param o the input argument
             */
            @Override
            public void accept(String o) {

            }
        };
    }

    public class PojoTest {
        private String test1;
        private List<String> test2;
    }
//        有一个list的数据结构如下，将list中test1和test2集合中的每个元素进行拼接，格式如下"test1|test2[0],test1|test2[1]"
}
