package com.docler.holdings.simplepingapp.reporting;

import java.util.Map;

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

	/**
	 * Create an json format string
	 * 
	 * @param valuesMap
	 * @return a String
	 */
	public String toJsonFormat(Map<String, String> valuesMap);
}
