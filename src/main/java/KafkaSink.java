import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KafkaSink {

    private final Logger logger = LoggerFactory.getLogger(KafkaSink.class);

    public KafkaSink() {

    }

    public void addSinkTopics(String dataTopic, DataStream<String> dataStream) throws IOException {
        if (dataTopic == null) {
            logger.error("No Destination topics found for Kafka Sink");
            return;
        }

        DataStreamSink<String> dataStreamUpdated;
        Properties kafkaProps = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("broker.properties");
        kafkaProps.load(inputStream);
        dataStreamUpdated = dataStream.addSink(new FlinkKafkaProducer<String>(dataTopic, new SimpleStringSchema(), kafkaProps)).name(dataTopic);

    }
}
