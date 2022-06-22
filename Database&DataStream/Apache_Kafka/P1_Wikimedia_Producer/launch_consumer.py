from kafka import KafkaConsumer

consumer = KafkaConsumer('demo_java', bootstrap_servers='localhost:9092')

for msg in consumer:
    print(msg)