# Project 1 

# Wikimedia Producer


At the following link https://stream.wikimedia.org/v2/stream/recentchange we found the real time stream of all the changes happening at wikimedia and this is the data stream we are going to use as input to apache kafka.

Similarly, but graphically, we can appreciate the changes in real time at wikimedia here https://esjewett.github.io/wm-eventsource-demo/


## How to proceed

Given the JAVA project folder `kafka-producer-wikimedia` where the instruction for the producer are defined,
the first thing to do is to create a topic (and pass the same name inside `WikimediaChangesProducer`) and then open a consumer to listen to the stream of data coming from wikimedia.

So, in order:

* start zookeeper and then kafka

```sh
zookeeper-server-start.sh -daemon ~/kafka_2.12-3.2.0/config/zookeeper.properties

kafka-server-start.sh ~/kafka_2.12-3.2.0/config/server.properties
```

* create a topic

```sh
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic [topic_name] --partitions [num_of_partitions] --replication-factor 1
```

* create a consumer on the topic

```sh
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic [topic_name]
```

for this we can use also a python script leveraging [`kafka-python`](launch_consumer.py)

* launch the JAVA program `WikimediaChangesProducer` to begin the stream


# OpenSearch Consumer

OpenSearch is a free edition of elasticSearch and we are going to use to consume the data provided by our wikimedia producer.

## How to proceed

First we need to generate the dockerized version of openSearch thanks to the `docker-compose.yml` file in folder `kafka-consumer-opensearch`

* if docker-composed not installed -> `sudo apt-get install docker-compose`
* `docker-compose up`: from the folder to activate the service

Now we have:

* on port `localhost:9200` the kafka connect
* on port `5601` the openSearch dashboard that we are going to use to explore our data stream with the `dev tool` console that will allow us to run REST API queries

Following the instruction at https://opensearch.org/docs/latest/, under the *docker quickstart* section we can try some command to see if we can perform basics operation on openSearch, like PUT, GET and creating/deleting an index

Once the both the `WikimediaChangesProducer` and the `OpenSearchConsumer` are set up, we can run them and respectively writing and reading from the same topic, at the same port (`demo_java @ localhost:9092`).

Once we have consumed some data from our topic, we can retrieve it using the `id` directly from openSearch dev tool:

* `GET /wikimedia/_doc/93319ce5-db00-4aac-9c59-63f83d277240`