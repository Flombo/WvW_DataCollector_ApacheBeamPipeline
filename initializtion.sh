#!/usr/bin/env bash

# Defining paths to custom js Server, kafka and zookeeper
jsServer="node /home/user01/Project/WvWDatacollector/main.js"
kafka="/home/user01/Project/Kafka/kafka_2.13-3.0.0/bin/kafka-server-start.sh config/server.properties"
zookeeper="/home/user01/Project/Kafka/kafka_2.13-3.0.0/bin/zookeeper-server-start.sh config/zookeeper.properties"

# set temporary location to kafka directory
cd /home/user01/Project/Kafka/kafka_2.13-3.0.0


# start zookeeper, kafka and js server as background processes
# sleep 10 seconds between each call
echo "starting zookeeper"
$zookeeper &
sleep 10
echo "starting kafka"
$kafka &
sleep 10
echo "starting js server"
$jsServer &
sleep 10
echo "all started"

# stay idle until a keypress occurs
read -rsp $'Press any key to shut down zookeeper, kafka and the js server...\n' -n1 key

# kill all background jobs
trap "exit" INT TERM
trap "kill 0" EXIT

