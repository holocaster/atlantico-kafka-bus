package br.gov.inst.atlan.kafkabus.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.email}")
    private String topicEmail;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(this.topicEmail)
                .partitions(10)
                .replicas(1)
                .build();
    }
}
