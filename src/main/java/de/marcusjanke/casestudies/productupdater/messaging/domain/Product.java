package de.marcusjanke.casestudies.productupdater.messaging.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.marcusjanke.casestudies.productupdater.jaxb.CDATAAdapter;

/**
 * Product
 * 
 * @author marcus
 *
 */
@XmlRootElement(name = "Product")
public class Product {

	private String id;
	private String name;
	private String description;
	private String sku;

	/**
	 * new Product
	 */
	public Product() {
	}

	/**
	 * new Product
	 * 
	 * @param id id
	 * @param name name
	 * @param description description
	 * @param sku SKU
	 */
	public Product(String id, String name, String description, String sku) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.sku = sku;
	}

	/**
	 * set id
	 * 
	 * @param id id
	 */
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * set name
	 * 
	 * @param name name
	 */
	@XmlElement(name = "Name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set description
	 * 
	 * @param description description
	 */
	@XmlElement(name = "Description")
	@XmlJavaTypeAdapter(CDATAAdapter.class)
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * set sku
	 * 
	 * @param sku SKU
	 */
	@XmlElement(name = "sku")
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * get id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * get name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (sku == null) {
			return other.sku == null;
		} else {
			return sku.equals(other.sku);
		}
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", sku=" + sku + "]";
	}

}