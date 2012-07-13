package org.apache.jmeter.plugins.html.extractor;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import org.apache.jmeter.gui.util.VerticalPanel;
import org.apache.jmeter.processor.gui.AbstractPostProcessorGui;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.gui.JLabeledTextField;

public class HtmlExtractorGui extends AbstractPostProcessorGui{
	
	/**
	 * @author Emir Ibrahimbegovic
	 * @since 1.0
	 */
	private static final long serialVersionUID = -6442844406115653088L;
	private JLabeledTextField htmlExtractVariableNameTextField = null;
	private JLabeledTextField htmlExtractJquerySelectorTextField = null;
	
	public HtmlExtractorGui(){
		super();
		init();
	}
	
	public void init()
	{
		setLayout(new BorderLayout());
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        
        VerticalPanel panel = new VerticalPanel();
        panel.setBorder(BorderFactory.createEtchedBorder());
        
        htmlExtractVariableNameTextField = new JLabeledTextField("Store result in variable: ");
        htmlExtractJquerySelectorTextField = new JLabeledTextField("Jquery selector: ");
        
        panel.add(htmlExtractVariableNameTextField);
        panel.add(htmlExtractJquerySelectorTextField);
        add(panel,BorderLayout.CENTER);
	}

	@Override
	public void clearGui(){
		super.clearGui();
		htmlExtractVariableNameTextField.setText("");
		htmlExtractJquerySelectorTextField.setText("");
	}
	
	@Override
	public TestElement createTestElement() {
		HtmlExtractor extractor = new HtmlExtractor();
		modifyTestElement(extractor);
		return extractor;
	}

	@Override
	public String getLabelResource() {
		return "Html Extractor";
	}
	
	@Override
	public String getStaticLabel() {
		return "Html Extractor";
	}

	@Override
	public void modifyTestElement(TestElement element) {
		super.configureTestElement(element);
		if (element instanceof HtmlExtractor)
		{
			HtmlExtractor extractor = (HtmlExtractor) element;
			extractor.setVar(htmlExtractVariableNameTextField.getText());
			extractor.setJquerySelector(htmlExtractJquerySelectorTextField.getText());
		}	
	}

	@Override
	public void configure(TestElement element){
		super.configure(element);
		if (element instanceof HtmlExtractor)
		{
			HtmlExtractor extractor = (HtmlExtractor) element;
			htmlExtractVariableNameTextField.setText(extractor.getVar());
			htmlExtractJquerySelectorTextField.setText(extractor.getJquerySelector());
		}
	}
}
