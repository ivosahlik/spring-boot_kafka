kafka-topics --bootstrap-server localhost:9092 --describe --topic spring-topic

## Kafka brooker - producer
kafka-console-producer --topic spring-topic --bootstrap-server localhost:9092

[appuser@ac7956c9980a ~]$ kafka-topics --bootstrap-server localhost:9092 --describe --topic spring-topic
Topic: spring-topic     TopicId: udtGkZSYRmewjXb0JAe32g PartitionCount: 3       ReplicationFactor: 1    Configs:
Topic: spring-topic     Partition: 0    Leader: 1       Replicas: 1     Isr: 1
Topic: spring-topic     Partition: 1    Leader: 1       Replicas: 1     Isr: 1
Topic: spring-topic     Partition: 2    Leader: 1       Replicas: 1     Isr: 1
[appuser@ac7956c9980a ~]$ kafka-console-producer --topic spring-topic --bootstrap-server localhost:9092
>helloworld
>helloworld
> 
> 
## Kafka brooker - consumer
kafka-console-consumer --topic spring-topic --bootstrap-server localhost:9092

[appuser@ac7956c9980a ~]$ kafka-console-consumer --topic spring-topic --bootstrap-server localhost:9092

New Event ID: 2631323b-7979-4dcc-9f22-d412c6846219
New Event ID: 4b71a3ee-2216-4780-8105-96d089fcefc7

