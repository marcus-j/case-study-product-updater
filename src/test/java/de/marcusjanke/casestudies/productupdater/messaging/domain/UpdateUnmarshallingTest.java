package de.marcusjanke.casestudies.productupdater.messaging.domain;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

/**
 * test updates JAXB mapping
 * 
 * @author marcus
 *
 */
public class UpdateUnmarshallingTest {

	private static final StockUpdate STOCK_UPDATE = new StockUpdate("BA-01", "pieces", 10);
	private static final DescriptionUpdate DESC_UPDATE = new DescriptionUpdate(new DescriptionUpdateProduct(
			"<p>Its use as a food originated in <a href=\"/wiki/Mexico\" title=\"Mexico\">Mexico</a>, and spread throughout the world following the <a href=\"/wiki/Spanish_colonization_of_the_Americas\" title=\"Spanish colonization of the Americas\">Spanish colonization of the Americas</a>. Tomato is consumed in diverse ways, including raw, as an ingredient in many dishes, sauces, <a href=\"/wiki/Salads\" class=\"mw-redirect\" title=\"Salads\">salads</a>, and drinks. While tomatoes are <a href=\"/wiki/Botanically\" class=\"mw-redirect\" title=\"Botanically\">botanically</a> <a href=\"/wiki/Berry_(botany)\" title=\"Berry (botany)\">berry</a>-type fruits, they are considered <a href=\"/wiki/Culinary_vegetable\" class=\"mw-redirect\" title=\"Culinary vegetable\">culinary vegetables</a>, being ingredients of savory meals.<sup id=\"cite_ref-3\" class=\"reference\"><a href=\"#cite_note-3\">[3]</a></sup></p>",
			"TO-02"));

	/**
	 * test unmarshalling of stock updates
	 *
	 */
	@Test
	public void testUpdateXMLUnmarshallingStockUpdate() throws JAXBException, IOException {
		try (InputStream is = UpdateUnmarshallingTest.class.getResourceAsStream("/updates/stock-update.xml")) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Update.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Update update = (Update) jaxbUnmarshaller.unmarshal(is);
			assertThat(update).isNotNull();
			assertThat(update.getStockUpdates()).isNotNull().isNotEmpty().contains(STOCK_UPDATE);
		}
	}

	/**
	 * test unmarshalling of description updates
	 *
	 */
	@Test
	public void testUpdateXMLUnmarshallingDescriptionUpdate() throws JAXBException, IOException {
		try (InputStream is = UpdateUnmarshallingTest.class.getResourceAsStream("/updates/description-update.xml")) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Update.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Update update = (Update) jaxbUnmarshaller.unmarshal(is);
			assertThat(update).isNotNull();
			assertThat(update.getDescriptionUpdates()).isNotNull().isNotEmpty().contains(DESC_UPDATE);
		}
	}
}
