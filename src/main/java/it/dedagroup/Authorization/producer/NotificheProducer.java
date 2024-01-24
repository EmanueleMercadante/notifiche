package it.dedagroup.Authorization.producer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import it.dedagroup.Authorization.dto.NotificaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificheProducer {

	private final RabbitTemplate rabbitTemplate;

    public String sendMessage(String queueName, NotificaDTO notificaDTO) {
        rabbitTemplate.convertAndSend(queueName, notificaDTO);
        return "notifica '" + notificaDTO.getMessaggio().toLowerCase() + "' prodotta!";
    }

    @RabbitListener(queues = "${producer.queue.feedback}")
	public void consume(@Payload  String message) {
			log.info("Nuovo feedback dal consumer: {}", message);
	}

}
