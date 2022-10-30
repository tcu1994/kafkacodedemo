import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemo {
	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(ProducerDemo.class);
		Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.3:2182");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "abdfc");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		
//		consumer.subscribe(Arrays.asList("third_topic"));
//		while(true) {
//			
//			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//			for (ConsumerRecord<String, String> record : records) {
//				logger.info("key: "+record.key() + ", value: "+ record.value());
//				logger.info("partition: "+record.partition() + ", offset: "+ record.offset());
//			}
//			
//		}
		
		
		
	}
}

