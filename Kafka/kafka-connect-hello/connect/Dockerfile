FROM confluentinc/cp-kafka-connect:latest

COPY ./my-config.properties /etc/kafka-connect/my-config.properties
COPY ./debezium-connector-postgres-2.4.0.Final-plugin.tar.gz /etc/kafka-connect/jars/
RUN tar xxf /etc/kafka-connect/jars/debezium-connector-postgres-2.4.0.Final-plugin.tar.gz --directory /etc/kafka-connect/jars
