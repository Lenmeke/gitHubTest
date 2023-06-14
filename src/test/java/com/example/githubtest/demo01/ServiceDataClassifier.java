package com.example.githubtest.demo01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务数据分类器
 */
public class ServiceDataClassifier {

    private static final String FILE_PATH = "output.txt";

    private Map<String, List<ServiceData>> classifiedData;

    public ServiceDataClassifier() {
        this.classifiedData = new HashMap<>();
    }

    /**
     * 对服务数据进行分类，并设置分类标签
     *
     * @param dataList 服务数据列表
     * @param label    分类标签
     */
    public void classify(List<ServiceData> dataList, String label) {
        for (ServiceData data : dataList) {
            String key = data.getServNumber() + "_" + label;
            if (!classifiedData.containsKey(key)) {
                classifiedData.put(key, new ArrayList<>());
            }
            List<ServiceData> classifiedList = classifiedData.get(key);
            if (!classifiedList.contains(data)) {
                classifiedList.add(data);
            }
        }
    }

    /**
     * 将分类处理好的数据写入文件
     */
    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (List<ServiceData> dataList : classifiedData.values()) {
                for (ServiceData data : dataList) {
                    String json = ServiceDataJsonAdapter.toJson(data);
                    writer.write(json);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务数据类
     */
    public static class ServiceData {

        private String servNumber;
        private String name;
        private int age;

        public ServiceData(String servNumber, String name, int age) {
            this.servNumber = servNumber;
            this.name = name;
            this.age = age;
        }

        public String getServNumber() {
            return servNumber;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    /**
     * 服务数据JSON适配器
     */
    public static class ServiceDataJsonAdapter {

        /**
         * 将服务数据对象转换为JSON字符串
         *
         * @param data 服务数据对象
         * @return JSON字符串
         */
        public static String toJson(ServiceData data) {
            // TODO: 实现将服务数据对象转换为JSON字符串的逻辑
            return "";
        }
    }

    /**
     * 服务数据工厂类
     */
    public static class ServiceDataFactory {

        /**
         * 根据servNumber、name和age创建服务数据对象
         *
         * @param servNumber 服务号码
         * @param name       姓名
         * @param age        年龄
         * @return 服务数据对象
         */
        public static ServiceData create(String servNumber, String name, int age) {
            return new ServiceData(servNumber, name, age);
        }
    }
}