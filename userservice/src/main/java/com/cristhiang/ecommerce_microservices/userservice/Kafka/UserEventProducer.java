package com.cristhiang.ecommerce_microservices.userservice.Kafka;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import com.cristhiang.ecommerce_microservices.userservice.Kafka.events.UserCreatedEvent;
import com.cristhiang.ecommerce_microservices.userservice.Kafka.events.UserUpdatedEvent;
import com.cristhiang.ecommerce_microservices.userservice.Kafka.events.UserDeletedEvent;

@Service 
public class UserEventProducer {
    
    private final KafkaTemplate<String, UserCreatedEvent> userCreatedKafkaTemplate;
    private final KafkaTemplate<String, UserUpdatedEvent> userUpdatedKafkaTemplate;
    private final KafkaTemplate<String, UserDeletedEvent> userDeletedKafkaTemplate;
    
    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;

    @Value("${kafka.topic.user-updated}")
    private String userUpdatedTopic; 

    @Value("${kafka.topic.user-deleted}")
    private String userDeletedTopic;

    @Value("${kafka.topic.user-login}")
    private String userLoginTopic;

    @Value("${kafka.topic.user-events-dlq}")
    private String userEventsDlqTopic;

    @Value("${kafka.topic.user-test-events}")
    private String userTestEventsTopic;

    public UserEventProducer(
            KafkaTemplate<String, UserCreatedEvent> userCreatedKafkaTemplate,
            KafkaTemplate<String, UserUpdatedEvent> userUpdatedKafkaTemplate,
            KafkaTemplate<String, UserDeletedEvent> userDeletedKafkaTemplate) {
        this.userCreatedKafkaTemplate = userCreatedKafkaTemplate;
        this.userUpdatedKafkaTemplate = userUpdatedKafkaTemplate;
        this.userDeletedKafkaTemplate = userDeletedKafkaTemplate;
    } 

    public void publishUserCreatedEvent(UserCreatedEvent event) {
        userCreatedKafkaTemplate.send(userCreatedTopic, event);
    }

    public void publishUserUpdatedEvent(UserUpdatedEvent event) {
        userUpdatedKafkaTemplate.send(userUpdatedTopic, event);
    }

    public void publishUserDeletedEvent(UserDeletedEvent event) {
        userDeletedKafkaTemplate.send(userDeletedTopic, event);
    }

    // Additional methods for other topics
    public void publishUserLoginEvent(Object event) {
        // Implementation for user login events
        // You can create a specific UserLoginEvent class if needed
    }

    public void publishToDlq(Object event) {
        // Implementation for dead letter queue
    }

    public void publishTestEvent(Object event) {
        // Implementation for test events
    }
}
