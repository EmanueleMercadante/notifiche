package it.dedagroup.Authorization.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.dedagroup.Authorization.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("validationErrorHandler")
public class ErrorHandlerConsumerRabbitMQ implements RabbitListenerErrorHandler {
	
	@Autowired
	FeedbackService feedbackService;
	
	@Value("${producer.queue.feedback}")
	private String feedbackQueue;

	@Override
	public Object handleError(
			Message message,
			org.springframework.messaging.Message<?> message1,
			ListenerExecutionFailedException e) throws Exception {
		log.error("Errore. Messaggio scartato.", e);
		
		feedbackService.sendFeedback(feedbackQueue, "Errore nella ricezione della notifica da parte del consumer");
		
		return null;
	}
} 

