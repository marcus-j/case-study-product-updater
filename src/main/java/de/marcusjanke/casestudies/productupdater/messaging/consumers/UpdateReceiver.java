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

import static java.lang.String.format;

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
	private Unmarshaller unmarshaller;
	@Autowired
	private StockedProductRepository stockedProductRepository;

	/**
	 * set up new ProductsReceiver
	 * 
	 * @throws JAXBException JAXBException
	 */
	public UpdateReceiver() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Update.class);
		unmarshaller = jaxbContext.createUnmarshaller();
	}

	/**
	 * receive message
	 * 
	 * @param message message
	 */
	@JmsListener(destination = "update-queue", containerFactory = "myFactory")
	public void receiveMessage(String message) {
		try {
			Update update = (Update) unmarshaller.unmarshal(new StringReader(message));
			logger.info(format("Received update message <%s>", update));
			saveUpdates(update);
		} catch (JAXBException e) {
			logger.error("Could not read update message", e);
		}
	}

	/**
	 * savre updates
	 * 
	 * @param update update
	 */
	private void saveUpdates(Update update) {
		if (Objects.nonNull(update.getStockUpdates())) {
			update.getStockUpdates().forEach(this::saveStockUpdate);
		}
		if (Objects.nonNull(update.getDescriptionUpdates())) {
			update.getDescriptionUpdates().forEach(this::saveDescriptionUpdate);
		}
	}

	/**
	 * save description updates
	 * 
	 * @param descriptionUpdate description update
	 */
	private void saveDescriptionUpdate(DescriptionUpdate descriptionUpdate) {
		List<StockedProduct> products = stockedProductRepository.findBySku(descriptionUpdate.getProduct().getSku());
		if (products.isEmpty()) {
			logger.warn(format("Could not update product description. SKU '%s' not found.",
					descriptionUpdate.getProduct().getSku()));
		} else {
			StockedProduct product = products.get(0);
			product.setDescription(descriptionUpdate.getProduct().getDescription());
			stockedProductRepository.save(product);
			logger.info(format("Updated description for product: %s", product));
		}
	}

	/**
	 * savre stock updates
	 * 
	 * @param stockUpdate stock update
	 */
	private void saveStockUpdate(StockUpdate stockUpdate) {
		List<StockedProduct> products = stockedProductRepository.findBySku(stockUpdate.getSku());
		if (products.isEmpty()) {
			logger.warn(format("Could not update product stock. SKU '%s' not found.", stockUpdate.getSku()));
		} else {
			StockedProduct product = products.get(0);
			product.setUnit(stockUpdate.getUnit());
			product.setQuantity(stockUpdate.getQuantity());
			stockedProductRepository.save(product);
			logger.info(format("Updated stock for product: %s", product));
		}
	}
}