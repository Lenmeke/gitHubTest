package com.example.job.configueration;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoordingnatorRegistryConfig {

    // 配置zk地址，调度任务的组名
    @Bean
    private static CoordinatorRegistryCenter createRegisterCenter(@Value("${elasticjob.zookeeper-url}") String zookeeperUrl, @Value("${elasticjob.group-name}") String group) {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(zookeeperUrl, group);
        zookeeperConfiguration.setSessionTimeoutMilliseconds(100);
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        return registryCenter;
    }

}
