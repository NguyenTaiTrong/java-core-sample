package com.amit.spring.config;

import com.amit.spring.queue.MessagePublisher;
import com.amit.spring.queue.MessagePublisherImpl;
import com.amit.spring.queue.RedisMessageSubscriberImpl;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
//@ComponentScan("com.amit.spring")
//@EnableRedisRepositories(basePackages = "com.amit.spring.repository")
//@PropertySource("classpath:application.properties")
@EnableCaching
public class RedisConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
//        return new JedisConnectionFactory();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6359);
        redisStandaloneConfiguration.setPort(8081);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        return  jedisConnectionFactory;
    }


    @Bean
    RedisTemplate<Integer, Object> redisTemplate(){
        final RedisTemplate<Integer, Object> template = new RedisTemplate<Integer, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("message");
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter(){
        return new MessageListenerAdapter(new RedisMessageSubscriberImpl());
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(), topic());
    }
}

