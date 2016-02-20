package com.docler.holdings.simplepingapp.helper;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * 
 * Test for {@link StreamReader}
 * 
 * 
 */
public class StreamReaderTest {

	@Test
	public void readStream_NullStream_ReturnEmptyString() throws IOException {
		String result = StreamReader.readStream(null);
		assertThat(result, is(""));
	}

	@Test
	public void readStream_ValidStream_ReturnAString() throws IOException {
		InputStream inputStream = new ByteArrayInputStream("Value".getBytes());
		String result = StreamReader.readStream(inputStream);
		assertThat(result, is("Value"));
	}
}
