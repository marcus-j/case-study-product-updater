package de.marcusjanke.casestudies.productupdater.jaxb;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import de.marcusjanke.casestudies.productupdater.jaxb.CDATAAdapter;

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
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMarshal() throws Exception {
		assertThat(adapter.marshal("a value")).isNotNull().isEqualTo("<![CDATA[a value]]>");
	}

	/**
	 * test CDATAAdapter unmarshalling
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnmarshal() throws Exception {
		assertThat(adapter.unmarshal("a value")).isNotNull().isEqualTo("a value");
	}

	/**
	 * test CDATAAdapter trimming while unmarshalling
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnmarshalTrimmed() throws Exception {
		assertThat(adapter.unmarshal("\na value  ")).isNotNull().isEqualTo("a value");
	}
}
