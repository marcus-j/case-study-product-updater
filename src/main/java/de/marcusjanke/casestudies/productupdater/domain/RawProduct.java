package de.marcusjanke.casestudies.productupdater.domain;

/**
 * Raw product with basic information, API only
 * 
 * @author marcus
 *
 */
public class RawProduct {

	private final String id;
	private final String name;
	private final String description;

	/**
	 * construct RawProduct from StockedProduct
	 * 
	 * @param stockedProduct
	 */
	public RawProduct(StockedProduct stockedProduct) {
		super();
		this.id = stockedProduct.getId();
		this.name = stockedProduct.getName();
		this.description = stockedProduct.getDescription();
	}

	/**
	 * construct RawProduct
	 * 
	 * @param id
	 * @param name
	 * @param description
	 */
	public RawProduct(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		RawProduct other = (RawProduct) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "RawProduct [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
