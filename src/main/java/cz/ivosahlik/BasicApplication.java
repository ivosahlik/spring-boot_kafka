package cz.ivosahlik;

import cz.ivosahlik.dto.Customer;
import cz.ivosahlik.event.Event;
import cz.ivosahlik.producer.MyProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@EnableKafka
@SpringBootApplication
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }
//
//    @Bean
//    public NewTopic topic() {
//        return TopicBuilder.name("spring-topic")
//                .partitions(3)
//                .replicas(1)
//                .build();
//    }
//
//	@Bean
//	public NewTopic errorTopic() {
//		return TopicBuilder.name("error-topic")
//				.partitions(3)
//				.replicas(1)
//				.build();
//	}
//
//    @Bean
//    public NewTopic retryableTopic() {
//        return TopicBuilder.name("retryable-topic")
//                .partitions(3)
//                .replicas(1)
//                .build();
//    }


    @Bean
    public ApplicationRunner runner(MyProducer producer) {

        var customer_1 = UUID.randomUUID();
        var customer_2 = UUID.randomUUID();
        var customer_3 = UUID.randomUUID();

        UUID[] customerIds = new UUID[] {customer_1, customer_2, customer_3};

        return args -> {
            while (true) {
//                producer.sendMessage("spring-topic", "New Event ID: " + UUID.randomUUID());

                var customer = new Customer(UUID.randomUUID(), "fake customer name", "customer address");

                var index = ThreadLocalRandom.current().nextInt(0, 3);

                var event = new Event<>(
                        customerIds[index],
                        "ADD_CUSTOMER",
                        Timestamp.from(Instant.now()),
                        customer
                );

                producer.sendMessage("spring-topic", customer.customerId().toString(), event);


                Thread.sleep(10000L);
            }



//            publishToRetryableTopic(producer);
//			startSimpleProducer(producer);
//            startProducer(producer);
        };
    }

//    private void publishToRetryableTopic(MyProducer producer) {
//        producer.sendMessage("retryable-topic", "RetryableEvent-1");
//    }
//
//	private void startSimpleProducer(MyProducer producer) {
//        producer.sendMessage("error-topic", "some message - 1");
//        producer.sendMessage("error-topic", "some message - 2");
//        producer.sendMessage("error-topic", "some message - 3");
//	}

//    private void startProducer(MyProducer producer) throws JsonProcessingException, InterruptedException {
//        var customerId_1 = UUID.randomUUID();
//        var customerId_2 = UUID.randomUUID();
//        var customerId_3 = UUID.randomUUID();
//        UUID[] customerIds = new UUID[]{customerId_1, customerId_2, customerId_3};
//
//        while (true) {
//            // 0, 1, 2
//            int index = ThreadLocalRandom.current().nextInt(0, 3);
//            System.out.println("index " + index);
//            Customer customer = new Customer(
//                    customerIds[index],
//                    "fake customer name",
//                    "Any address"
//            );
//
//            MyEvent<Customer> event = new MyEvent<>(
//                    UUID.randomUUID(),
//                    customer,
//                    Timestamp.from(Instant.now())
//            );
//
//            producer.sendMessage("spring-topic", customer.customerId(), event);
//
//            Thread.sleep(10000L);
//        }
//    }

//    @Bean
//    public ObjectMapper mapper() {
//        return new ObjectMapper();
//    }
//
//    @Bean
//    public KafkaListenerErrorHandler errorHandler() {
//        return (message, exception) -> {
//            System.out.println("inside error handler" + message);
//            throw exception;
////            return "FAILED";
//        };
//    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
//            ConsumerFactory<String, String> consumerFactory) {
//        final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        factory.setCommonErrorHandler(defaultErrorHandler());
//
//        factory.getContainerProperties().setDeliveryAttemptHeader(true);
//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
//        return factory;
//    }
//
//    @Bean
//    public DefaultErrorHandler defaultErrorHandler() {
//       var defaultErrorHandler = new DefaultErrorHandler(
//               (consumerRecord, exception) -> {
//                  System.out.println("inside DefaultErrorHandler");
//               },
//               new FixedBackOff(1000L, 2)
//       );
//
//       defaultErrorHandler.setAckAfterHandle(false);
//
//       defaultErrorHandler.addNotRetryableExceptions(
//               NotRetryableException.class
//       );
//
//       defaultErrorHandler.addRetryableExceptions(
//               RetryableException.class
//       );
//
//       defaultErrorHandler.setCommitRecovered(false);
//
//       return defaultErrorHandler;
//    }

}
