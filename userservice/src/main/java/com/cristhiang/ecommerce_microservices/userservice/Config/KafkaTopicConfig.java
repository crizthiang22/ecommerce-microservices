package com.cristhiang.ecommerce_microservices.userservice.Config;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Value("${kafka.replication-factor:1}")
    private short replicationFactor;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);

        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic userCreatedTopic() {
        return TopicBuilder.name("user-created")
                .partitions(5)
                .replicas(replicationFactor)
                .config(TopicConfig.RETENTION_MS_CONFIG,"604800000" )
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "lz4")
                .config(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1048576")
                .build();
    }

    @Bean
    public NewTopic userUpdatedTopic() {
        return TopicBuilder.name("user-updated")
                .partitions(4)
                .replicas(replicationFactor)
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "lz4")
                .config(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, "1")
                .build();
    }
    
    @Bean
    public NewTopic userDeletedTopic() {
        return TopicBuilder.name("user-deleted")
                .partitions(2)  
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, "2592000000")  
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
                .config(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, String.valueOf(Math.max(1, replicationFactor - 1)))
                .config(TopicConfig.UNCLEAN_LEADER_ELECTION_ENABLE_CONFIG, "false")
                .build();
    }

    @Bean
    public NewTopic userLoginEventsTopic() {
        return TopicBuilder.name("user-login-events")
                .partitions(12)  
                .replicas(replicationFactor)
                .config(TopicConfig.RETENTION_MS_CONFIG, "1209600000") 
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
                .config(TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG, String.valueOf(Math.max(1, replicationFactor - 1)))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "snappy") 
                .build();
    }

    //Dead letter queue 
    @Bean
    public NewTopic userEventsDlqTopic() {
        return TopicBuilder.name("user-events-dlq")
                .partitions(1)  
                .replicas(replicationFactor)
                .config(TopicConfig.RETENTION_MS_CONFIG, "2592000000")
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "none")  
                .build();
    }

    //For future testing implementation 
    // for example with production:  @org.springframework.context.annotation.Profile("!prod")
    @Bean
    public NewTopic userTestEventsTopic() {
        return TopicBuilder.name("user-test-events")
                .partitions(1)
                .replicas(1)  
                .config(TopicConfig.RETENTION_MS_CONFIG, "3600000")  
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
                .build();
    }

}
