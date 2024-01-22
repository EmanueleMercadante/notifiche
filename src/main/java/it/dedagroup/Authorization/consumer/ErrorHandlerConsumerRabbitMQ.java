package it.dedagroup.Authorization.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("validationErrorHandler")
public class ErrorHandlerConsumerRabbitMQ implements RabbitListenerErrorHandler {

	@Override
	public Object handleError(
			Message message,
			org.springframework.messaging.Message<?> message1,
			ListenerExecutionFailedException e) throws Exception {
		log.error("Errore. Messaggio scartato.", e);
		return null;
	}
}
