package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPrintingPanel extends JPanel {

	private static final long serialVersionUID = 6677719495197182007L;
	private final JTextArea textArea;
	
	public TextPrintingPanel() {
		this.textArea = new JTextArea();
		this.initialize();
	}
	
	public void initialize() {
		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		this.textArea.setEditable(false);
		scrollPane.add(this.textArea);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void print(String toPrint) {
		this.getTextArea().append(toPrint);
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
