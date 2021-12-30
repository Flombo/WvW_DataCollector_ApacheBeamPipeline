#!/usr/bin/env bash

# Defining paths to custom js Server, kafka and zookeeper
jsServer="/home/user01/Project/WvWDatacollector/main.js"
kafka="/home/user01/Project/Kafka/kafka_2.13-3.0.0/bin/kafka-server-start.sh config/server.properties"
zookeeper="/home/user01/Project/Kafka/kafka_2.13-3.0.0/bin/zookeeper-server-start.sh config/zookeeper.properties"

# set temporary location to kafka directory
cd /home/user01/Project/Kafka/kafka_2.13-3.0.0

#start zookeeper, kafka and js server as background processes
# sleep 5 seconds between each call
$zookeeper &
sleep 5
$kafka &
sleep 5
node $jsServer &

#TODO: kill background processes cleanly