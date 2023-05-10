package com.example.githubtest.userToWork;

import com.example.githubtest.PojoTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToWork1 {
    public static void main(String[] args) {
        List<PojoTest> list = new ArrayList<>();
        PojoTest pojo1 = new PojoTest();
        pojo1.setTest1("A");
        pojo1.setTest2(Arrays.asList("1", "2"));
        PojoTest pojo2 = new PojoTest();
        pojo2.setTest1("B");
        pojo2.setTest2(Collections.emptyList());
        list.add(pojo1);
        list.add(pojo2);

        String result = list.stream()
                .flatMap(pojo -> pojo.getTest2().isEmpty() ? Stream.of("") : pojo.getTest2().stream()
                        .map(test2Value -> String.format("%s|%s", pojo.getTest1(), test2Value)))
                .collect(Collectors.joining(","));

        System.out.println(result);
    }
}
