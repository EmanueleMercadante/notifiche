package it.dedagroup.Authorization.service;

public interface FeedbackService {
	
	void sendFeedback(String feedbackQueueName, String message);
}
