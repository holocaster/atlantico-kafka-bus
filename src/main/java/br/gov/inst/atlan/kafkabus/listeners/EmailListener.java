package br.gov.inst.atlan.kafkabus.listeners;

import br.gov.inst.atlan.kafkabus.events.EmailEvent;
import br.gov.inst.atlan.kafkabus.repositories.AdminUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailListener implements RabbitListenerConfigurer {

    @Value("${kafka.topic.email}")
    private String topicEmail;

    @Autowired
    private KafkaTemplate<String, EmailEvent> producer;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "${userapi.rabbitmq.queue}")
    public void listener(EmailEvent emailEvent) {
        log.info("Recebendo email do rabbitmq: {}", emailEvent);

        log.info("Enviando email para o kafka");
        try {
            this.producer.send(this.topicEmail, emailEvent);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }


    @KafkaListener(topics = "${kafka.topic.email}", groupId = "${kafka.topic.email.consumerGroupName}")
    public void listen(EmailEvent emailEvent) {
        log.info("Recebendo email do kafka: {}", emailEvent);

        log.info("Encontrando todos administradores");
        this.adminUserRepository.findAll().forEach(user -> {
            log.info("Enviando email para {}", user.getId());
        });

        log.info("Enviando email para usuário do evento recebido: {}", emailEvent.getUserId());
    }
}
