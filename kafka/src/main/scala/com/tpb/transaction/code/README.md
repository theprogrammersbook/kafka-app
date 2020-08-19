we are checking here about the transactions in Kafka.

we have ensure the following ..

1. topic should have replication factor >=3
2. topic should have in sync replica >=2

So for this purpose we have to create topics with the above properties.
Have to make sure the folloiwng .
ProducerConfig.TRANSACTIONAL_ID_CONFIG

