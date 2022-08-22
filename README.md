# Event-Driven-Architectures-for-Spring-Developers

<img width="542" alt="Screenshot 2022-08-22 at 3 20 15 PM" src="https://user-images.githubusercontent.com/54174687/185893046-426045d3-caa4-4a88-b8e3-e098b3b173a0.png">

# Maxwell Daemon

https://maxwells-daemon.io/

```sh
Configure Mysql

# /etc/my.cnf

[mysqld]
# maxwell needs binlog_format=row
binlog_format=row
server_id=1 
log-bin=master
```


```sh
mysql> CREATE USER 'maxwell'@'%' IDENTIFIED BY 'XXXXXX';
mysql> CREATE USER 'maxwell'@'localhost' IDENTIFIED BY 'XXXXXX';

mysql> GRANT ALL ON maxwell.* TO 'maxwell'@'%';
mysql> GRANT ALL ON maxwell.* TO 'maxwell'@'localhost';

mysql> GRANT SELECT, REPLICATION CLIENT, REPLICATION SLAVE ON *.* TO 'maxwell'@'%';
mysql> GRANT SELECT, REPLICATION CLIENT, REPLICATION SLAVE ON *.* TO 'maxwell'@'localhost';
```

# Start MAxwell

```sh
prateekashtikar@Prateeks-MacBook-Pro ~ % maxwell --user maxwell --password maxwell --producer=kafka --kafka.bootstrap.servers=localhost:9092 --kafka_topic=maxwell 
593 [WARN] MaxwellConfig: maxwell mysql host not specified, defaulting to localhost
541731 [WARN] NetworkClient: [Producer clientId=producer-1] Error while fetching metadata with correlation id 8 : {maxwell=LEADER_NOT_AVAILABLE}
```

# 

```sh
curl -X POST http://localhost:8080/update/USD/1000
```

<img width="1193" alt="Screenshot 2022-08-22 at 3 26 00 PM" src="https://user-images.githubusercontent.com/54174687/185894207-231eb0a6-1919-4b22-8896-276f0b49ac00.png">

```
2022-08-22 15:12:09.383  INFO 11924 --- [currency1-0-C-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-currency1-1, groupId=currency1] Seeking to EARLIEST offset of partition currency-0
2022-08-22 15:12:09.412  INFO 11924 --- [currency1-0-C-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-currency1-1, groupId=currency1] Resetting offset for partition currency-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 1.0, KeyUSD
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {USD=1.0}
2022-08-22 15:21:07.411  INFO 11924 --- [currency1-0-C-1] org.apache.kafka.clients.NetworkClient   : [Consumer clientId=consumer-currency1-1, groupId=currency1] Node -1 disconnected.
```

---------

curl -X POST http://localhost:8080/update/GBP/782

```
2022-08-22 15:12:09.383  INFO 11924 --- [currency1-0-C-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-currency1-1, groupId=currency1] Seeking to EARLIEST offset of partition currency-0
2022-08-22 15:12:09.412  INFO 11924 --- [currency1-0-C-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-currency1-1, groupId=currency1] Resetting offset for partition currency-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 1.0, KeyUSD
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {USD=1.0}
2022-08-22 15:21:07.411  INFO 11924 --- [currency1-0-C-1] org.apache.kafka.clients.NetworkClient   : [Consumer clientId=consumer-currency1-1, groupId=currency1] Node -1 disconnected.
2022-08-22 15:34:35.421  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 0.782, KeyGBP
2022-08-22 15:34:35.421  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {GBP=0.782, USD=1.0}
```
<img width="1198" alt="Screenshot 2022-08-22 at 3 35 26 PM" src="https://user-images.githubusercontent.com/54174687/185895999-ff5b3b9d-97fc-49d6-9876-7ca034d47068.png">

---------

curl -X POST http://localhost:8080/update/EUR/800

```
2022-08-22 15:12:09.412  INFO 11924 --- [currency1-0-C-1] o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-currency1-1, groupId=currency1] Resetting offset for partition currency-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 1.0, KeyUSD
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {USD=1.0}
2022-08-22 15:21:07.411  INFO 11924 --- [currency1-0-C-1] org.apache.kafka.clients.NetworkClient   : [Consumer clientId=consumer-currency1-1, groupId=currency1] Node -1 disconnected.
2022-08-22 15:34:35.421  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 0.782, KeyGBP
2022-08-22 15:34:35.421  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {GBP=0.782, USD=1.0}
2022-08-22 15:38:59.033  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 0.8, KeyEUR
2022-08-22 15:38:59.034  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {EUR=0.8, GBP=0.782, USD=1.0}
```

<img width="1140" alt="Screenshot 2022-08-22 at 3 40 43 PM" src="https://user-images.githubusercontent.com/54174687/185897042-c7299842-ec55-407a-b1d8-dd7007d0bcc9.png">

---------

curl -X POST http://localhost:8080/delete/EUR

```
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 1.0, KeyUSD
2022-08-22 15:12:37.389  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {USD=1.0}
2022-08-22 15:21:07.411  INFO 11924 --- [currency1-0-C-1] org.apache.kafka.clients.NetworkClient   : [Consumer clientId=consumer-currency1-1, groupId=currency1] Node -1 disconnected.
2022-08-22 15:34:35.421  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 0.782, KeyGBP
2022-08-22 15:34:35.421  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {GBP=0.782, USD=1.0}
2022-08-22 15:38:59.033  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: 0.8, KeyEUR
2022-08-22 15:38:59.034  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {EUR=0.8, GBP=0.782, USD=1.0}
2022-08-22 15:41:22.113  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : === Rate: null, KeyEUR
2022-08-22 15:41:22.121  INFO 11924 --- [currency1-0-C-1] com.example.consumer.KafkaConsumer       : Currencies now: {GBP=0.782, USD=1.0}
```

<img width="1190" alt="Screenshot 2022-08-22 at 3 42 10 PM" src="https://user-images.githubusercontent.com/54174687/185897295-61d979d1-2a9f-470f-9ccf-3de11fcdcd5d.png">



