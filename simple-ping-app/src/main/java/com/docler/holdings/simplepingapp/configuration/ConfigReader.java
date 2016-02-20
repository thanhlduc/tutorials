package com.docler.holdings.simplepingapp.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Configuration Reader
 */
public enum ConfigReader {
	INSTANCE;

	private static final String FILE_NOT_FOUND = "File not found ";
	private static final String KEY_NOT_NULL = "Key not null ";

	public static final String REPORT_HOST = "reportHost";
	public static final String SURVEY_HOST1 = "jasminHost";
	public static final String SURVEY_HOST2 = "oranumHost";

	private transient String fileName = "simplePingApp.properties";
	private transient Properties props;
	private transient final Logger logger = Logger.getLogger(ConfigReader.class);

	/**
	 * Constructor
	 * 
	 */
	private ConfigReader() {
		init();
	}

	private void init() {
		props = new Properties();
		try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
			if (inputStream == null) {
				throw new FileNotFoundException(FILE_NOT_FOUND + fileName);
			}
			props.load(inputStream);
		} catch (IOException ex) {
			String msg = FILE_NOT_FOUND + fileName;
			logger.error(msg, ex);
		}
	}

	/**
	 * Get value from a given key
	 * 
	 * @param key
	 * @return string value if exist
	 */
	public String getProperty(String key) {
		if (StringUtils.isEmpty(key)) {
			logger.error(KEY_NOT_NULL);
			throw new IllegalArgumentException(KEY_NOT_NULL);
		}
		return props.getProperty(key);
	}

}
