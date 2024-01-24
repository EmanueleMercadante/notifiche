package it.dedagroup.Authorization.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import it.dedagroup.Authorization.service.FeedbackService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService{

	private final RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendFeedback(String feedbackQueueName, String message) {
        rabbitTemplate.convertAndSend(feedbackQueueName, message);
    }

}
