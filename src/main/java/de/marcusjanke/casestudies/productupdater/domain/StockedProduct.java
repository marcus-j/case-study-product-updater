package de.marcusjanke.casestudies.productupdater.domain;

/**
 * Stocked product with full information. (Stock inluded in same table 
 * in order to not having to deal with stock updates for non-existing data 
 * (not in scope of this exercise)) 
 * 
 * @author marcus
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class StockedProduct {

	@Id
	@NotEmpty
	private String id;
	private String name;
	@Lob
	@Column(columnDefinition = "TEXT", length = 65535)
	private String description;
	private String sku;
	private String unit;
	private int quantity;

	/**
	 * new StockedProduct
	 */
	public StockedProduct() {
		super();
	}

	/**
	 * new StockedProduct
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param sku
	 * @param unit
	 * @param quantity
	 */
	public StockedProduct(String id, String name, String description, String sku, String unit, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.sku = sku;
		this.unit = unit;
		this.quantity = quantity;
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
	 * set id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * set name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * set description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get SKU
	 * 
	 * @return SKU
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * set SKU
	 * 
	 * @param sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * get unit
	 * 
	 * @return unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * set unit
	 * 
	 * @param unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * get quantity
	 * 
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * set quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		StockedProduct other = (StockedProduct) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity != other.quantity)
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", sku=" + sku + ", unit=" + unit + ", quantity=" + quantity
				+ "]";
	}

}
