package com.docler.holdings.simplepingapp.config;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;

/**
 * Tester {@link ConfigReader}
 */
public class ConfigReaderTest {
	private transient ConfigReader configReader; 
	
	public ConfigReaderTest() throws NoSuchFieldException, IllegalAccessException {
		configReader= ConfigReader.INSTANCE;
		Whitebox.setInternalState(configReader, "fileName", "src/test/resources/simplePingApp.properties");
	}

	@Test
	public void getProperty_Key_jasminHost_Return_jasminDotcom(){
		assertThat(configReader.getProperty("jasminHost"), is("jasmin.com"));
	}
	
	@Test
	public void getProperty_UnknownKey_ReturnNull(){
		assertThat(configReader.getProperty("unknown")==null, is(true));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getProperty_KeyNull_ReturnException(){
		configReader.getProperty(null);
	}
}
