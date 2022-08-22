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
