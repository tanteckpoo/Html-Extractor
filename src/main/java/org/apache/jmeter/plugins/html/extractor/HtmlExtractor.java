package org.apache.jmeter.plugins.html.extractor;

import org.apache.jmeter.processor.PostProcessor;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterVariables;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlExtractor extends AbstractTestElement implements PostProcessor {

	/**
	 * @author Emir Ibrahimbegovic
	 * @since 1.0
	 */
	private static final long serialVersionUID = -8857155525407718646L;
	private final String JQUERY_SELECTOR = "JQUERY_SELECTOR";
	private final String VAR = "VAR";

	public HtmlExtractor() {
		super();
	}

	public String getJquerySelector() {
		return getPropertyAsString(JQUERY_SELECTOR);
	}

	public void setJquerySelector(String jquerySelector) {
		setProperty(JQUERY_SELECTOR, jquerySelector);
	}

	public String getVar() {
		return getPropertyAsString(VAR);
	}

	public void setVar(String var) {
		setProperty(VAR, var);
	}

	public String extractHtmlValueWithJquerySelector(String html, String jquerySelector) throws Exception {
		Document doc = Jsoup.parse(html);
		String value = doc.select(jquerySelector).text();
		if(null == jquerySelector){
			throw new Exception("Invalid jquery selector");
		}else {
			return value;
		}
	}

	@Override
	public void process() {
		JMeterContext context = getThreadContext();

		final SampleResult previousResult = context.getPreviousResult();
		if (previousResult == null) {
			return;
		}

		JMeterVariables vars = context.getVariables();
		String responseData = context.getPreviousResult().getResponseDataAsString();

		try {
			String response = extractHtmlValueWithJquerySelector(responseData, getJquerySelector());
			vars.put(getVar(), response);
		}catch (Exception e) {
			System.out.println("Extraction failed!. Invalid jquery selector: " + e.getMessage());
		}
	}

}
