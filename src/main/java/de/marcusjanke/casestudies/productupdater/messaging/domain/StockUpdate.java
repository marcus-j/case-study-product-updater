package de.marcusjanke.casestudies.productupdater.messaging.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * StockUpdate
 * 
 * @author marcus
 *
 */
@XmlRootElement(name = "Stock")
public class StockUpdate {

	private String sku;
	private String unit;
	private int quantity;

	/**
	 * new StockUpdate
	 */
	public StockUpdate() {
	}

	/**
	 * new StockUpdate
	 * 
	 * @param sku SKU
	 * @param unit uni
	 * @param quantity quantity
	 */
	StockUpdate(String sku, String unit, int quantity) {
		super();
		this.sku = sku;
		this.unit = unit;
		this.quantity = quantity;
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
	 * @param unit unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * get quantitiy
	 * 
	 * @return quantitiy
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * set quantity
	 * 
	 * @param quantity quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	 * @param sku SKU
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		StockUpdate other = (StockUpdate) obj;
		if (quantity != other.quantity)
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku)) {
			return false;
		}
		if (unit == null) {
			return other.unit == null;
		} else {
			return unit.equals(other.unit);
		}
	}

	@Override
	public String toString() {
		return "Stock [sku=" + sku + ", unit=" + unit + ", quantity=" + quantity + "]";
	}
	
}