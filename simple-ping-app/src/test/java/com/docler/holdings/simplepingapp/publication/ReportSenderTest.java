package com.docler.holdings.simplepingapp.publication;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.docler.holdings.simplepingapp.configuration.ConfigReader;
import com.docler.holdings.simplepingapp.reporting.IReportFactory;
import com.docler.holdings.simplepingapp.reporting.Report;
import com.docler.holdings.simplepingapp.reporting.ReportFactory;
import com.docler.holdings.simplepingapp.reporting.ReportSender;

/**
 * 
 * Test for ReportSender
 *
 */
public class ReportSenderTest {

	private transient ReportSender sender;
	private transient IReportFactory factory;

	@Before
	public void setUp() {
		sender = ReportSender.INSTANCE;
		factory = ReportFactory.INSTANCE;
	}

	@Test
	public void post_ValidURL_Return200() throws Exception {
		Report report = new Report();
		report.setHostName("host");
		report.setIcmpPing("icmp");
		report.setTcpPing("tcpip");
		report.setTraceRoute("trace");
		String content = factory.toJsonFormat(report);

		assertThat(sender.post(ConfigReader.INSTANCE.getProperty(ConfigReader.REPORT_HOST), content), is(200));
	}

	@Test
	public void post_InvalidURL_ReturnMinus1() throws Exception {
		Report report = new Report();
		report.setHostName("host");
		report.setIcmpPing("icmp");
		report.setTcpPing("tcpip");
		report.setTraceRoute("trace");
		String content = factory.toJsonFormat(report);

		assertThat(sender.post("invalid url", content), is(-1));
	}
}
