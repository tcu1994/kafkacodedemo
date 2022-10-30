import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemo {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.3:2182");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		
		final Logger logger = LoggerFactory.getLogger(ProducerDemo.class);
		
		KafkaProducer<String, String> producer = new KafkaProducer(props);
		ProducerRecord<String, String> record = new ProducerRecord("third_topic", "hello world!");
		
		producer.send(record, new Callback() {
			public void onCompletion(RecordMetadata recordMetadata, Exception e) {
				if (e== null) {
					logger.info("Metadata: \n" + "topic: "+ recordMetadata.topic()+ "\n" +
								"partition: "+ recordMetadata.partition() + "\n"+ 
									"offset: "+ recordMetadata.offset());
				}
			}
		});
		producer.flush();
		
	}
}