package de.marcusjanke.casestudies.productupdater.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.marcusjanke.casestudies.productupdater.domain.RawProduct;
import de.marcusjanke.casestudies.productupdater.domain.StockedProduct;
import de.marcusjanke.casestudies.productupdater.repositories.StockedProductRepository;

/**
 * Product resource
 * 
 * @author marcus
 *
 */
@Controller
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private StockedProductRepository stockedProductRepository;

	/**
	 * List all products with id, name and description, available using
	 * http://localhost:8080/products/
	 * 
	 * @return List of all products with id, name and description
	 */
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<RawProduct>> getProducts() {
		try {
			List<RawProduct> products = StreamSupport.stream(stockedProductRepository.findAll().spliterator(), false)
					.map(RawProduct::new).collect(Collectors.toList());
			if (products.isEmpty()) {
				return new ResponseEntity<List<RawProduct>>(Collections.emptyList(), HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<List<RawProduct>>(products, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Could not handle request", e);
			return new ResponseEntity<List<RawProduct>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Show one product with all available information including current stock,
	 * available using e.g
	 * http://localhost:8080/products/43b105a0-b5da-401b-a91d-32237ae418e4
	 * 
	 * @param id
	 * @return one product with all available information including current stock
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<StockedProduct> getProduct(@PathVariable("id") String id) {
		try {
			StockedProduct product = stockedProductRepository.findOne(id);
			if (Objects.nonNull(product)) {
				return new ResponseEntity<StockedProduct>(product, HttpStatus.OK);
			} else {
				return new ResponseEntity<StockedProduct>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Could not handle request", e);
			return new ResponseEntity<StockedProduct>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
