//package com.banktalib.notificationservice.NotificationsService.Config;
//
//import com.banktalib.UserClient.TransactionDto;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.TopicBuilder;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaTopicConfig {
//
//    @Value("Notifications-events")
//    private String topicName;
//
//    private String bootstrapAddress = "localhost:9092";
//    private String groupId="notifications";
//
//
//    // spring bean for kafka topic
//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name(topicName)
//                .build();
//    }
//
//    @Bean
//    public ConsumerFactory<String, TransactionDto> consumerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//        // Configure JsonDeserializer
//        JsonDeserializer<TransactionDto> deserializer = new JsonDeserializer<>(TransactionDto.class);
//        deserializer.addTrustedPackages("com.banktalib.paymentservice.PaymentService.Dto");
//
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, TransactionDto> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, TransactionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//}