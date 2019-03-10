package de.marcusjanke.casestudies.productupdater.repositories;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.*;
import static org.awaitility.Awaitility.await;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import de.marcusjanke.casestudies.productupdater.domain.StockedProduct;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StockedProductRepositoryTest {

	@Autowired
	private
	StockedProductRepository stockedProductRepository;

	/**
	 * test repository is in context
	 */
	@Test
	public void contextLoads() {
		assertThat(stockedProductRepository).isNotNull();
	}

	@Before
	public void waitForDataToBeAvailable() {
		await().atMost(15, SECONDS).until(() -> stockedProductRepository.count() > 0);
	}

	/**
	 * test findBySku
	 */
	@Test
	public void testFindBySku() {
		StockedProduct product = new StockedProduct("id1", "name", "description", "sku1", "unit", 1);
		stockedProductRepository.save(product);
		assertThat(stockedProductRepository.findBySku("sku1").get(0)).isNotNull().isEqualTo(product);
	}

	/**
	 * test save and retrieve
	 */
	@Test
	public void testSaveAndFind() {
		StockedProduct product = new StockedProduct("id2", "name", "description", "sku2", "unit", 1);
		stockedProductRepository.save(product);
		assertThat(stockedProductRepository.findOne(product.getId())).isNotNull().isEqualTo(product);
	}
}
