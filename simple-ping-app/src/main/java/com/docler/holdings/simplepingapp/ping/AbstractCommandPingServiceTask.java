package com.docler.holdings.simplepingapp.ping;

import java.io.IOException;
import java.io.InputStream;

import com.docler.holdings.simplepingapp.helper.StreamReader;

/**
 * 
 * Abstract ping service task by using command
 *
 */
public abstract class AbstractCommandPingServiceTask extends AbstractPingServiceTask {

	/**
	 * Default constructor
	 */
	public AbstractCommandPingServiceTask() {
		super();
	}

	/**
	 * Constructor with params
	 */
	public AbstractCommandPingServiceTask(String url) {
		super(url);
	}

	/**
	 * Process a ping command and Parse response
	 * 
	 * @param pingCmd
	 * @return a String response
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected String processPingCommand(String pingCmd) throws IOException, InterruptedException {
		String result;
		Process myProcess = Runtime.getRuntime().exec(pingCmd);
		myProcess.waitFor();
		result = parseResponse(myProcess);
		return result;
	}

	/**
	 * Parse response from an input stream
	 * 
	 * @param myProcess
	 * @return a string
	 * @throws IOException
	 */
	protected String parseResponse(Process myProcess) throws IOException {
		String result;
		if (myProcess.exitValue() == 0) {
			InputStream inputStream = myProcess.getInputStream();
			result = StreamReader.readStream(inputStream);
		} else {
			InputStream inputStream = myProcess.getInputStream();
			result = StreamReader.readStream(inputStream);
		}
		return result;
	}
}
