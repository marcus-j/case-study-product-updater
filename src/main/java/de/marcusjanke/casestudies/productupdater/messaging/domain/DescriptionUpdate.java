package de.marcusjanke.casestudies.productupdater.messaging.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * DescriptionUpdate
 * 
 * @author marcus
 *
 */
@XmlRootElement(name = "Description")
public class DescriptionUpdate {

	private DescriptionUpdateProduct product;

	/**
	 * new DescriptionUpdate
	 */
	public DescriptionUpdate() {
		super();
	}

	/**
	 * new DescriptionUpdate
	 * 
	 * @param product
	 */
	public DescriptionUpdate(DescriptionUpdateProduct product) {
		super();
		this.product = product;
	}

	/**
	 * get DescriptionUpdateProduct
	 * @return DescriptionUpdateProduct
	 */
	public DescriptionUpdateProduct getProduct() {
		return product;
	}

	/**
	 * set DescriptionUpdateProduct
	 * @param product
	 */
	@XmlElement(name = "Product")
	public void setProduct(DescriptionUpdateProduct product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		DescriptionUpdate other = (DescriptionUpdate) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DescriptionUpdate [product=" + product + "]";
	}

}
