package de.marcusjanke.casestudies.productupdater.controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import de.marcusjanke.casestudies.productupdater.domain.RawProduct;
import de.marcusjanke.casestudies.productupdater.domain.StockedProduct;
import de.marcusjanke.casestudies.productupdater.repositories.StockedProductRepository;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.*;

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
	@GetMapping(value = "/products")
	public @ResponseBody ResponseEntity<List<RawProduct>> getProducts() {
		try {
			List<RawProduct> products = StreamSupport.stream(stockedProductRepository.findAll().spliterator(), false)
					.map(RawProduct::new).collect(toList());
			return new ResponseEntity<>(products, products.isEmpty() ? NOT_FOUND : OK);
		} catch (Exception e) {
			logger.error("Could not handle request", e);
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Show one product with all available information including current stock,
	 * available using e.g
	 * http://localhost:8080/products/43b105a0-b5da-401b-a91d-32237ae418e4
	 * 
	 * @param id product id
	 * @return one product with all available information including current stock
	 */
	@GetMapping(value = "/products/{id}")
	public @ResponseBody ResponseEntity<StockedProduct> getProduct(@PathVariable("id") String id) {
		try {
			StockedProduct product = stockedProductRepository.findOne(id);
			if (Objects.nonNull(product)) {
				return new ResponseEntity<>(product, OK);
			} else {
				return new ResponseEntity<>(NOT_FOUND);
			}
		} catch (Exception e) {
			logger.error("Could not handle request", e);
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}
}
