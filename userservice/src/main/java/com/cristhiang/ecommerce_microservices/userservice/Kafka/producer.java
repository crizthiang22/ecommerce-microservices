import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;



@Service 
public class UserEventProducer {
    private final kafkaTemplate<String, UserCreatedEvent> kafkaTemplate;
    
    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;

    public UserEventProducer(kafkaTemplate<String, UserCreatedEvent>kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    } 

    public void publishUserCreatedEvent (UserCreatedEvent, event) {
        kafkaTemplate.send(userCreatedTopic.event);
    }

    @Value("${kafka.topic.user-updated}")
    private String userUpdatedTopic; 

    public UserEventProducer(kafkaTemplate<String, UserUpdatedEvent>kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate; 
    }

    public void publishUserUpdatedEvent (UserUpdatedEvent, event) {
        kafkaTemplate.send(userUpdatedTopic.event);
    }

    @Value("${kafka.topic.user-deleted}")
    private String userDeletedTopic;

    public UserEventProducer (kafkaTemplate<String,UserDeletedEvent>kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserDeletedEvent (UserDeletedEvent, event) {
        kafkaTemplate.send(userDeletedTopic.event);
    }

    // add the producer events for userLoginEventsTopic, userEventsDlqTopic
    // userEventsDlqTopic, userTestEventsTopic 
}
