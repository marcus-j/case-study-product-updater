package de.marcusjanke.casestudies.productupdater.jaxb;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

/**
 * test CDATAAdapter
 * 
 * @author marcus
 *
 */
public class CDATAAdapterTest {

	private final CDATAAdapter adapter = new CDATAAdapter();

	/**
	 * test CDATAAdapter marshalling
	 */
	@Test
	public void testMarshal() {
		assertThat(adapter.marshal("a value")).isNotNull().isEqualTo("<![CDATA[a value]]>");
	}

	/**
	 * test CDATAAdapter unmarshalling
	 */
	@Test
	public void testUnmarshal() {
		assertThat(adapter.unmarshal("a value")).isNotNull().isEqualTo("a value");
	}

	/**
	 * test CDATAAdapter trimming while unmarshalling
	 */
	@Test
	public void testUnmarshalTrimmed() {
		assertThat(adapter.unmarshal("\na value  ")).isNotNull().isEqualTo("a value");
	}
}
