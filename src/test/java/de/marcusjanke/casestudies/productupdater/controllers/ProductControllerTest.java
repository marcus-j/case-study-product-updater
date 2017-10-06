package de.marcusjanke.casestudies.productupdater.controllers;

import static org.assertj.core.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.marcusjanke.casestudies.productupdater.controllers.ProductController;
import de.marcusjanke.casestudies.productupdater.repositories.StockedProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

	@Autowired
	private ProductController productController;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private StockedProductRepository stockedProductRepository;
	
	@Before
	public void waitForDataToBeAvailable() {
		try {
			Thread.sleep(15_000L);
		} catch (InterruptedException e) {}
		assertThat(stockedProductRepository.findAll()).isNotNull().isNotEmpty();
	}
	
	/**
	 * test controller is in context
	 */
	@Test
	public void contextLoads() throws Exception {
		assertThat(productController).isNotNull();
		assertThat(mockMvc).isNotNull();
	}

	/**
	 * test products are returned
	 */
	@Test
	public void testGetProductsReturnsRawProducts() throws Exception {
		assertThat(this.mockMvc.perform(get("/products")).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString()).contains("\"name\"").doesNotContain("\"quantity\"");
	}

	/**
	 * test product with full info is returned by id
	 */
	@Test
	public void testGetProductById() throws Exception {
		assertThat(this.mockMvc.perform(get("/products/43b105a0-b5da-401b-a91d-32237ae418e4")).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString()).contains("\"quantity\"");
	}
}
