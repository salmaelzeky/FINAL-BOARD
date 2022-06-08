package com.todoist.server.config.List;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String MESSAGE_QUEUE = "board_queue";
    public static final String REPLY_QUEUE = "reply_queue";
    public static final String EXCHANGE = "exchange";


    /** *
     * Set sending RPCQueue message
     Configure the Send Message Queue*/
    @Bean
    Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE);
    }
    /** *
     * Return Queue Configuration
     */
    @Bean
    Queue replyQueue() {

        return new Queue(REPLY_QUEUE);
    }
    /** *
     * Switch setting
     */
    @Bean
    TopicExchange exchange() {

        return new TopicExchange(EXCHANGE);
    }
    /** *
     * Queuing and Switch Link Request
     */
    @Bean
    Binding messageBinding() {

        return BindingBuilder.bind(messageQueue()).to(exchange()).with(MESSAGE_QUEUE);
    }
    /** *
     * Back to Queue and Switch Link
     */
    @Bean
    Binding replyBinding() {

        return BindingBuilder.bind(replyQueue()).to(exchange()).with(REPLY_QUEUE);
    }
    /** *
     * Use RabbitTemplate Send and receive messages
     * And set callback queue address
     */
    @Bean
    RabbitTemplate boardRabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(REPLY_QUEUE);
        template.setReplyTimeout(10000);
        return template;
    }
    /** *
     * Configure listener for return queue
     */
    @Bean
    SimpleMessageListenerContainer boardReplyContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(REPLY_QUEUE);
        container.setMessageListener(boardRabbitTemplate(connectionFactory));
        return container;
    }

}
