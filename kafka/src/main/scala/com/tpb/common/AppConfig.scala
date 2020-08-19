package com.tpb.common

object AppConfig{
  val applicationID = "ProducerApp"
  val bootstrapServers = "localhost:9092,localhost:9093"
  val topicName = "employee"
  val numEvents = 1000000
}
