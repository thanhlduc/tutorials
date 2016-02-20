package com.docler.holdings.simplepingapp.reporting;

/**
 * 
 * Creation report contract
 *
 */
public interface IReportFactory {
	/**
	 * Create an json format string
	 * 
	 * @param report
	 * @return a string
	 */
	public String toJsonFormat(Report report);
}
