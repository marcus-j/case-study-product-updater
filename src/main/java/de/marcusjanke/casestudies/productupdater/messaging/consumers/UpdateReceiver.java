package de.marcusjanke.casestudies.productupdater.messaging.consumers;

import java.io.StringReader;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import de.marcusjanke.casestudies.productupdater.domain.StockedProduct;
import de.marcusjanke.casestudies.productupdater.messaging.domain.DescriptionUpdate;
import de.marcusjanke.casestudies.productupdater.messaging.domain.StockUpdate;
import de.marcusjanke.casestudies.productupdater.messaging.domain.Update;
import de.marcusjanke.casestudies.productupdater.repositories.StockedProductRepository;

/**
 * Receives JMS update messages and saves to DB
 * 
 * XML deserialization is done using JAXB, Jackson would be possible as
 * well (often better performance, scenario-dependent)
 * 
 * @author marcus
 *
 */
@Component
public class UpdateReceiver {

	private final Logger logger = LoggerFactory.getLogger(UpdateReceiver.class);
	private Unmarshaller jaxbUnmarshaller;
	@Autowired
	private StockedProductRepository stockedProductRepository;

	/**
	 * set up new ProductsReceiver
	 * 
	 * @throws JAXBException
	 */
	public UpdateReceiver() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Update.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	}

	/**
	 * receive message
	 * 
	 * @param message
	 */
	@JmsListener(destination = "update-queue", containerFactory = "myFactory")
	public void receiveMessage(String message) {
		try {
			Update update = (Update) jaxbUnmarshaller.unmarshal(new StringReader(message));
			logger.info("Received update message <" + update + ">");
			saveUpdates(update);
		} catch (JAXBException e) {
			logger.error("Could not read update message", e);
		}
	}

	/**
	 * savre updates
	 * 
	 * @param update
	 */
	private void saveUpdates(Update update) {
		if (Objects.nonNull(update.getStockUpdates())) {
			update.getStockUpdates().stream().forEach(this::saveStockUpdate);
		}
		if (Objects.nonNull(update.getDescriptionUpdates())) {
			update.getDescriptionUpdates().stream().forEach(this::saveDescriptionUpdate);
		}
	}

	/**
	 * save description updates
	 * 
	 * @param descriptionUpdate
	 */
	private void saveDescriptionUpdate(DescriptionUpdate descriptionUpdate) {
		List<StockedProduct> products = stockedProductRepository.findBySku(descriptionUpdate.getProduct().getSku());
		if (products.size() > 0) {
			StockedProduct product = products.get(0);
			product.setDescription(descriptionUpdate.getProduct().getDescription());
			stockedProductRepository.save(product);
			logger.info(String.format("Updated description for product: %s", product));
		} else {
			logger.warn(String.format("Could not update product description. SKU '%s' not found.",
					descriptionUpdate.getProduct().getSku()));
		}
	}

	/**
	 * savre stock updates
	 * 
	 * @param stockUpdate
	 */
	private void saveStockUpdate(StockUpdate stockUpdate) {
		List<StockedProduct> products = stockedProductRepository.findBySku(stockUpdate.getSku());
		if (products.size() > 0) {
			StockedProduct product = products.get(0);
			product.setUnit(stockUpdate.getUnit());
			product.setQuantity(stockUpdate.getQuantity());
			stockedProductRepository.save(product);
			logger.info(String.format("Updated stock for product: %s", product));
		} else {
			logger.warn(String.format("Could not update product stock. SKU '%s' not found.", stockUpdate.getSku()));
		}
	}
}