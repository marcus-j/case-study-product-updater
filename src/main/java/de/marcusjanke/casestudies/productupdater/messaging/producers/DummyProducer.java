package de.marcusjanke.casestudies.productupdater.messaging.producers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Produce dummy messages from resources
 * 
 * @author marcus
 *
 */
@Component
public class DummyProducer implements ProductsProducer, UpdateProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	private final ScheduledExecutorService execService1 = Executors.newScheduledThreadPool(4);
	private final Logger logger = LoggerFactory.getLogger(DummyProducer.class);

	/**
	 * new DummyProducer
	 */
	public DummyProducer() {
		execService1.scheduleAtFixedRate(this::produceProductsMessage, 5, 60, TimeUnit.SECONDS);
		execService1.scheduleAtFixedRate(this::produceUpdateMessage, 10, 10, TimeUnit.SECONDS);
		logger.info(String.format("Started DummyProducer"));
	}

	/**
	 * new dummy products message
	 */
	@Override
	public void produceProductsMessage() {
        String path = "/products/products.xml";
        try {
			sendMessage(path, "product-queue");
    		logger.info(String.format("Sent products message based on '%s'", path));
		} catch (IOException e) {
    		logger.error(String.format("Could not send new products message based on '%s'", path), e);
		}
	}

	/**
	 * new dummy update message
	 */
	@Override
	public void produceUpdateMessage() {
        String path = String.format("/products/update-0%s.xml", ThreadLocalRandom.current().nextInt(1, 3));
        try {
			sendMessage(path, "update-queue");
    		logger.info(String.format("Sent update message based on '%s'", path));
		} catch (IOException e) {
    		logger.error(String.format("Could not send new update message based on '%s'", path), e);
		}
	}
	
	/**
	 * set JMS message
	 * 
	 * @param path
	 * @param queue
	 * @throws IOException
	 */
	private void sendMessage(String path, String queue) throws IOException {
		logger.info(String.format("Sending message based on '%s' to queue '%s'", path, queue));
		try (InputStream is = DummyProducer.class.getResourceAsStream(path); BufferedReader buffer = new BufferedReader(new InputStreamReader(is))) {
     		jmsTemplate.convertAndSend(queue, buffer.lines().collect(Collectors.joining("\n")));
        } catch (IOException e)  {
    		throw e;
        }
	}
}
