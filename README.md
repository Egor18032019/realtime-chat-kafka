# introductionKafka
    Apache Kafka начало изучение
Запуск  зукипера
 ./zkServer.cmd start  запустить зукипер 
* Запуск кафки
	start zookeeper-server-start.bat c:\kafka_2.13-3.5.0\config\zookeeper.
потом кафку
//		start kafka-server-start.bat c:\kafka_2.13-3.5.0\config\server.properties
Создаем топик
//.\kafka-topics.bat  --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
//.\kafka-topics.bat  --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic kafka-chat
Список уже созданых топиков
// .\kafka-topics.bat --zookeeper localhost:9092 --list