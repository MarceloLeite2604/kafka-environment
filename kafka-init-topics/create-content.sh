#!/bin/bash

/opt/bitnami/kafka/bin/kafka-topics.sh --create \
  --topic users \
  --partitions 3 \
  --replication-factor 1 \
  --if-not-exists \
  --bootstrap-server kafka-2:9092;

/opt/bitnami/kafka/bin/kafka-topics.sh --create \
  --topic messages \
  --partitions 2 \
  --replication-factor 1 \
  --if-not-exists \
  --bootstrap-server kafka-1:9093;