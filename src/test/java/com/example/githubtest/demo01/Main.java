package com.example.githubtest.demo01;

import com.google.common.collect.Lists;
import net.minidev.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 定义一个服务类，表示每个服务的信息
class Service {
    public String servNumber;   // 服务号码
    public String tag;          // 服务标签

    public Service(String servNumber, String tag) {
        this.servNumber = servNumber;
        this.tag = tag;
    }

    public String toJson() {
        // 将服务信息转换为 JSON 格式的字符串
        JSONObject json = new JSONObject();
        json.put("servNumber", servNumber);
        json.put("tag", tag);
        return json.toString();
    }
}

// 定义一个分类器接口，用于不同的分类器实现
interface Classifier {
    String classify(Service service);
    int priority();
}

// 定义一个服务分类器，根据不同的条件对服务进行分类
class ServiceClassifier {
    private List<Classifier> classifiers;

    public ServiceClassifier(List<Classifier> classifiers) {
        this.classifiers = classifiers;
    }

    public void classifyServices(List<Service> services) {
        Map<String, String> tagMap = new HashMap<>();   // 用于记录服务标签的map

        // 遍历服务列表，逐个分类并设置标签
        for (Service service : services) {
            String servNumber = service.servNumber;
            String tag = null;
            int priority = Integer.MAX_VALUE;

            // 遍历所有分类器，选择优先级最高的分类器进行分类
            for (Classifier classifier : classifiers) {
                if (classifier.priority() <= priority) {
                    String newTag = classifier.classify(service);
                    if (tagMap.containsKey(servNumber) && !tagMap.get(servNumber).equals(newTag)) {
                        // 如果服务号码已经存在，且与当前服务的标签不同，则忽略当前分类器
                        continue;
                    }
                    tag = newTag;
                    priority = classifier.priority();
                }
            }

            // 将服务标签设置到服务信息中
            service.tag = tag;

            // 将服务标签记录到标签map中
            tagMap.put(servNumber, tag);
        }
    }
}

// 定义一个服务分类器工厂，用于创建不同的分类器
class ServiceClassifierFactory {
    public static Classifier createClassifier(String condition) {
        switch (condition) {
            case "condition1":
                return new Classifier() {
                    public String classify(Service service) {
                        // 根据条件1分类服务
                        return "tag1";
                    }

                    @Override
                    public int priority() {
                        return 0;
                    }
                };
            case "condition2":
                return new Classifier() {
                    public String classify(Service service) {
                        // 根据条件2分类服务
                        return "tag2";
                    }

                    @Override
                    public int priority() {
                        return 0;
                    }
                };
            // 其他条件的分类器实现
            default:
                throw new IllegalArgumentException("Invalid condition: " + condition);
        }
    }
}

// 主程序
public class Main {
    public static void main(String[] args) {
        // 从数据库中查询服务信息
        List<Service> services = queryServicesFromDatabase();

        // 创建服务分类器
        Classifier classifier = ServiceClassifierFactory.createClassifier("condition1");
        ServiceClassifier serviceClassifier = new ServiceClassifier(Lists.newArrayList(classifier));

        // 对服务进行分类并设置标签
        serviceClassifier.classifyServices(services);

        // 将分类好的服务信息写入文件
        writeServicesToJsonFile(services);
    }

    private static List<Service> queryServicesFromDatabase() {
        // 从数据库中查询服务信息
        List<Service> services = new ArrayList<>();
        // 执行查询操作...
        return services;
    }

    private static void writeServicesToJsonFile(List<Service> services) {
        try (Writer writer = new FileWriter("services.json")) {
            for (Service service : services) {
                // 将服务信息转换为 JSON 格式，并写入到文件中
                String json = service.toJson();
                writer.write(json);
                writer.write("\n");   // 每行服务信息使用换行符分隔
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}