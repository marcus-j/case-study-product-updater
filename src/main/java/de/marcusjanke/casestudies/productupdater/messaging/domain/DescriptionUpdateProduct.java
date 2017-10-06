package de.marcusjanke.casestudies.productupdater.messaging.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.marcusjanke.casestudies.productupdater.jaxb.CDATAAdapter;

/**
 * DescriptionUpdateProduct
 * 
 * @author marcus
 *
 */
@XmlRootElement(name = "Product")
public class DescriptionUpdateProduct {

	private String description;
	private String sku;

	/**
	 * new DescriptionUpdateProduct
	 */
	public DescriptionUpdateProduct() {
		super();
	}

	/**
	 * new DescriptionUpdateProduct
	 * 
	 * @param description
	 * @param sku
	 */
	public DescriptionUpdateProduct(String description, String sku) {
		super();
		this.description = description;
		this.sku = sku;
	}

	/**
	 * set description
	 * 
	 * @param description
	 */
	@XmlValue
	@XmlJavaTypeAdapter(CDATAAdapter.class)
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * set SKU
	 * 
	 * @param sku
	 */
	@XmlAttribute(name = "sku")
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * get description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * get SKU
	 * 
	 * @return SKU
	 */
	public String getSku() {
		return sku;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		DescriptionUpdateProduct other = (DescriptionUpdateProduct) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DescriptionUpdateProduct [description=" + description + ", sku=" + sku + "]";
	}

}
