
* Запуск кафки
* 
  *Start Zookeeper*
 ``` 
start zookeeper-server-start.bat c:\kafka_2.13-3.5.0\config\zookeeper
 ```
*Start Kafka*
``` 
start kafka-server-start.bat c:\kafka_2.13-3.5.0\config\server.properties
 ```
*Создаем топик*
```
.\kafka-topics.bat  --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
```
```
.\kafka-topics.bat  --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic kafka-chat
```
*Список уже созданных топиков*
```
.\kafka-topics.bat --bootstrap-server localhost:9092 --list
 ```
*Консоль потребителя*
```
./kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafka-chat
```