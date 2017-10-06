package de.marcusjanke.casestudies.productupdater.messaging.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Products
 * 
 * @author marcus
 *
 */
@XmlRootElement(name = "Products")
public class Products {

	@XmlElement(name = "Product")
	private List<Product> products;

	/**
	 * new Products
	 * 
	 * @param products
	 */
	public Products(List<Product> products) {
		super();
		this.products = products;
	}

	/**
	 * new Products
	 */
	public Products() {
	}

	/**
	 * set product list
	 * 
	 * @param products
	 */
	public void setProduct(List<Product> products) {
		this.products = products;
	}

	/**
	 * get product list
	 *  
	 * @return product list
	 */
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Products [products=" + products + "]";
	}

}