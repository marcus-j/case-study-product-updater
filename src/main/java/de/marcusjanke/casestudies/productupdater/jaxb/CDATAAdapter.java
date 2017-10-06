package de.marcusjanke.casestudies.productupdater.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter that handles CDATA element values
 * 
 * @author marcus
 *
 */
public class CDATAAdapter extends XmlAdapter<String, String> {

	/**
	 * marshal CDATA content
	 */
	@Override
	public String marshal(String value) throws Exception {
		return "<![CDATA[" + value + "]]>";
	}

	/**
	 * unmarshal CDATA content (also trims)
	 */
	@Override
	public String unmarshal(String value) throws Exception {
		return value.trim();
	}

}