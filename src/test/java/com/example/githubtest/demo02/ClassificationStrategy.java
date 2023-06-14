package com.example.githubtest.demo02;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

import lombok.Data;
import net.minidev.json.JSONObject;


interface ClassificationStrategy {

    boolean matches(Predicate<Service> predicate);
    String getLabel();
    int getPriority();
}

class Condition1Strategy implements ClassificationStrategy {

    private String label;
    private int priority;

    public Condition1Strategy() {
        this.label = "tag1";
    }

    public Condition1Strategy(int priority) {
        this.label = "tag1";
        this.priority = priority;
    }

    @Override
    public boolean matches(Predicate<Service> predicate) {
        return predicate.test((Service) predicate);
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }
    // ...
}

class Condition2Strategy implements ClassificationStrategy {
    @Override
    public boolean matches(Service Service) {
        return false;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }
    // ...
}


class ClassificationStrategyFactory {
    public static List<ClassificationStrategy> getStrategies() {
        List<ClassificationStrategy> strategies = new ArrayList<>();
        strategies.add(new Condition1Strategy(1).matches(service -> service.getServNumber() && ));
        strategies.add(new Condition2Strategy());
        // ...
        return strategies;
    }
}


public class DataClassifier {
    public static void main(String[] args) {

        // 获取待分类数据
        List<Service> dataList = fetchDataFromDatabase();

        // 获取分类器集合
        List<ClassificationStrategy> strategies = ClassificationStrategyFactory.getStrategies();

        Map<String, Service> resultMap = new LinkedHashMap<>();

        for (Service service : dataList) {
            for (ClassificationStrategy strategy : strategies) {
                if (strategy.matches(service)) {
                    service.setLabel(strategy.getLabel());
                    String key = service.getServNumber() + "_" + service.getLabel();
                    if (!resultMap.containsKey(key)) {
                        resultMap.put(key, service);
                    }
                    break;
                }
            }
        }

        writeToFile(resultMap.values());
    }

    private static List<Service> fetchDataFromDatabase() {
        // 实现从数据库中查询数据的逻辑
        return null;
    }

    private static void writeToFile(Collection<Service> classifiedDataList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FileWriter fileWriter = new FileWriter("output.json", false);

            for (Service Service : classifiedDataList) {
                String json = objectMapper.writeValueAsString(Service);
                fileWriter.write(json);
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 定义一个服务类，表示每个服务的信息
@Data
class Service {
    public String servNumber;   // 服务号码
    public String label;          // 服务标签

    public Service(String servNumber, String tag) {
        this.servNumber = servNumber;
        this.label = tag;
    }

    public String toJson() {
        // 将服务信息转换为 JSON 格式的字符串
        JSONObject json = new JSONObject();
        json.put("servNumber", servNumber);
        json.put("tag", label);
        return json.toString();
    }
}