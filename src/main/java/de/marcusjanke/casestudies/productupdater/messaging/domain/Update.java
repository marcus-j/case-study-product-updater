package de.marcusjanke.casestudies.productupdater.messaging.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Update
 * 
 * @author marcus
 *
 */
@XmlRootElement(name = "Update")
public class Update {

	private List<StockUpdate> stockUpdates;
	private List<DescriptionUpdate> descriptionUpdates;

	/**
	 * set stock updates
	 * 
	 * @param stockUpdates stock updates
	 */
	@XmlElement(name = "Stock")
	public void setStockUpdates(List<StockUpdate> stockUpdates) {
		this.stockUpdates = stockUpdates;
	}

	/**
	 * set description updates
	 * 
	 * @param descriptionUpdates description updates
	 */
	@XmlElement(name = "Description")
	public void setDescriptionUpdates(List<DescriptionUpdate> descriptionUpdates) {
		this.descriptionUpdates = descriptionUpdates;
	}

	/**
	 * get description updates
	 * 
	 * @return description updates
	 */
	public List<DescriptionUpdate> getDescriptionUpdates() {
		return descriptionUpdates;
	}

	/**
	 * get stock updates
	 * 
	 * @return stock updates
	 */
	public List<StockUpdate> getStockUpdates() {
		return stockUpdates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriptionUpdates == null) ? 0 : descriptionUpdates.hashCode());
		result = prime * result + ((stockUpdates == null) ? 0 : stockUpdates.hashCode());
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
		Update other = (Update) obj;
		if (descriptionUpdates == null) {
			if (other.descriptionUpdates != null)
				return false;
		} else if (!descriptionUpdates.equals(other.descriptionUpdates)) {
			return false;
		}
		if (stockUpdates == null) {
			return other.stockUpdates == null;
		} else {
			return stockUpdates.equals(other.stockUpdates);
		}
	}

	@Override
	public String toString() {
		return "Update [stockUpdates=" + stockUpdates + ", descriptionUpdates=" + descriptionUpdates + "]";
	}

}