import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerThread implements Runnable {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		final Runnable mythread = new ConsumerThread(latch);
		Thread mythread1 = new Thread(mythread);
		mythread1.start();
		
		
		
		
		try {
			latch.await();
		}catch(Exception e) {
			System.out.println("error");
		}
		
	}
	private CountDownLatch latch;
	private KafkaConsumer<String, String> consumer;
	final Logger logger = LoggerFactory.getLogger(ProducerDemo.class);
	public ConsumerThread(CountDownLatch latch) {
		Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.3:2182");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "abddsasdafc");
		
		
		
		consumer = new KafkaConsumer<String, String>(props);
		this.latch = latch;
		consumer.subscribe(Arrays.asList("third_topic"));
	}
	
	public void run() {
		try {
			while(true) {
				
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					logger.info("key: "+record.key() + ", value: "+ record.value());
					logger.info("partition: "+record.partition() + ", offset: "+ record.offset());
				}
				
			}
		} catch (WakeupException e) {
			logger.info("shutdown");
		} finally {
			consumer.close();
			latch.countDown();
		}
		
		
	}
	public void shutdown() {
		consumer.wakeup();
			
	}
}