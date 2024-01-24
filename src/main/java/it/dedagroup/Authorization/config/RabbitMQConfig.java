package it.dedagroup.Authorization.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class RabbitMQConfig {

	@Bean
	MessageConverter converter(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
		return new Jackson2JsonMessageConverter(objectMapper);
	}
	
	@Bean
    Queue notificheQueue() {
        return new Queue("notifiche", true);
    }
	
	@Bean
    Queue feedbackQueue() {
        return new Queue("feedback", true);
    }
}
