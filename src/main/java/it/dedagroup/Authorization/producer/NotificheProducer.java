package it.dedagroup.Authorization.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.dedagroup.Authorization.dto.NotificaDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificheProducer {

	private final RabbitTemplate rabbitTemplate;

    public String sendMessage(String queueName, NotificaDTO notificaDTO) {
        rabbitTemplate.convertAndSend(queueName, notificaDTO);
        return "notifica '" + notificaDTO.getMessaggio().toLowerCase() + "' prodotta!";
    }
}
