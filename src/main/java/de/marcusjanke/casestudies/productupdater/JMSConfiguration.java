package de.marcusjanke.casestudies.productupdater;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * JMS Configuration using embedded ActiveMQ
 * 
 * @author marcus
 *
 */
@Configuration
public class JMSConfiguration {

	/**
	 * JmsListenerContainerFactory
	 * 
	 * @param connectionFactory ConnectionFactory
	 * @param configurer DefaultJmsListenerContainerFactoryConfigurer
	 * @return JmsListenerContainerFactory
	 */
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}

}
