package com.docler.holdings.simplepingapp.cache;

import java.util.Date;

/**
 * 
 * Result for a ping
 *
 */
public class PingResult {
	private String pingResult;
	private Date pingDate;

	public PingResult() {

	}

	/**
	 * @return the pingResult
	 */
	public String getPingResult() {
		return pingResult;
	}

	/**
	 * @param pingResult
	 *            the pingResult to set
	 */
	public void setPingResult(String pingResult) {
		this.pingResult = pingResult;
	}

	/**
	 * @return the pingDate
	 */
	public Date getPingDate() {
		return pingDate;
	}

	/**
	 * @param pingDate
	 *            the pingDate to set
	 */
	public void setPingDate(Date pingDate) {
		this.pingDate = pingDate;
	}

}
