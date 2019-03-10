package de.marcusjanke.casestudies.productupdater.messaging.consumers;

import java.io.StringReader;
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
import de.marcusjanke.casestudies.productupdater.messaging.domain.Product;
import de.marcusjanke.casestudies.productupdater.messaging.domain.Products;
import de.marcusjanke.casestudies.productupdater.repositories.StockedProductRepository;

import static java.lang.String.format;

/**
 * Receives JMS product messages and saves to DB
 * 
 * XML deserialization is done using JAXB, Jackson would be possible as
 * well (often better performance, scenario-dependent)
 *  
 * @author marcus
 *
 */
@Component
public class ProductsReceiver {

	private final Logger logger = LoggerFactory.getLogger(ProductsReceiver.class);
	private Unmarshaller jaxbUnmarshaller;
	@Autowired
	private StockedProductRepository stockedProductRepository;

	/**
	 * set up new ProductsReceiver
	 * 
	 * @throws JAXBException JAXBException delegation
	 */
	public ProductsReceiver() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	}

	/**
	 * receive message
	 * 
	 * @param message message
	 */
	@JmsListener(destination = "product-queue", containerFactory = "myFactory")
	public void receiveMessage(String message) {
		try {
			Products products = (Products) jaxbUnmarshaller.unmarshal(new StringReader(message));
			logger.info(format("Received product message <%s>", products));
			saveProducts(products);
		} catch (JAXBException e) {
			logger.error("Could not read products message", e);
		}
	}

	/**
	 * save received products
	 * 
	 * @param products received products
	 */
	private void saveProducts(Products products) {
		products.getProducts().stream().map(this::mapToStockedProduct).forEach(this::saveProduct);
	}
	
	/**
	 * map Product instance to StockedProduct instance
	 * 
	 * @param product product
	 * @return StockedProduct
	 */
	private StockedProduct mapToStockedProduct(Product product) {
		return new StockedProduct(product.getId(), product.getName(), product.getDescription(), product.getSku(), null, 0);
	}

	/**
	 * savre StockedProduct instance to DB
	 * 
	 * @param product product
	 */
	private void saveProduct(StockedProduct product) {
		StockedProduct originalProduct = stockedProductRepository.findOne(product.getId());
		if (Objects.nonNull(originalProduct)) {
			originalProduct.setDescription(product.getDescription());
			originalProduct.setSku(product.getSku());
			stockedProductRepository.save(originalProduct);
			logger.info(format("Updated product: %s", originalProduct));
		} else {
			stockedProductRepository.save(product);
			logger.info(format("Saved product: %s", product));
		}
	}
}