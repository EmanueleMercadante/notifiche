package it.dedagroup.Authorization.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import it.dedagroup.Authorization.dto.NotificaDTO;
import it.dedagroup.Authorization.service.NotificaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificheConsumer {

	private final NotificaService notificaService;

	@RabbitListener(queues = "${consumer.queue.notifiche}", errorHandler = "validationErrorHandler")
	public void consume(@Payload NotificaDTO notificaDTO) {
			log.info("Nuova notifica: {}", notificaDTO);
			NotificaDTO notifica = notificaService.creaNotifica(notificaDTO);
			log.info("Notifica salvata. {}", notifica);
	}
}
