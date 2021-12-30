#Instructions

1. Kafka Cheatsheet 
   ```
   Init
   bin/zookeeper-server-start.sh config/zookeeper.properties
   bin/kafka-server-start.sh config/server.properties 
   
   Create topic 
   bin/kafka-topics.sh --create --topic 2-2 --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 
   
   Read topic
   bin/kafka-console-consumer.sh --topic 2-1 --from-beginning --bootstrap-server localhost:9092 
   
   List active topics
   bin/kafka-topics.sh --list --bootstrap-server localhost:9092 
   
   Delete topic
   bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic test2 
   
   Delete multiple topics with regex
   bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic '2-.*'
   ```

2. Pipeline execution
   1. Go to ~/Projects/Java/
   2. Run mvn -X compile exec:java -Dexec.mainClass=java/Main -Dexec.args="Topic-Name"