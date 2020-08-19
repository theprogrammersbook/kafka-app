package com.tpb.common

import java.util.{Date, Properties, Random}

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory

object AppProducer extends App {
  val loggerFactory = LoggerFactory.getLogger(this.getClass)
  val events = 100000
  val topic = "employee"
  val brokers = "localhost:9092"
  val rnd = new Random()
  val props = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
  props.put(ProducerConfig.CLIENT_ID_CONFIG, "AppProducer")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)


  val producer = new KafkaProducer[String, String](props)
  val t = System.currentTimeMillis()
  for (nEvents <- Range(0, events)) {
    val runtime = new Date().getTime()
    val ip = "192.168.2." + rnd.nextInt(255)
    val msg = runtime + "," + nEvents + ",www.example.com," + ip
    val data = new ProducerRecord[String, String](topic, ip, msg)
    producer.send(data)
  }

  System.out.println("sent per second: " + events * 1000 / (System.currentTimeMillis() - t))
  producer.close()
}
