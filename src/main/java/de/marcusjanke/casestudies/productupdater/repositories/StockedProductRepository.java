package de.marcusjanke.casestudies.productupdater.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.marcusjanke.casestudies.productupdater.domain.StockedProduct;

/**
 * StockedProduct repository 
 * 
 * @author marcus
 *
 */
public interface StockedProductRepository extends CrudRepository<StockedProduct, String> {

	/**
	 * find StockedProduct by SKU
	 * @param sku
	 * @return StockedProduct
	 */
	List<StockedProduct> findBySku(String sku);

}
