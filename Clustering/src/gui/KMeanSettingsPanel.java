package gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.SettingsPanelController;

public class KMeanSettingsPanel extends JPanel {

	private static final long serialVersionUID = -6222364906825547375L;
	private final SettingsPanelController controller;
	private final JLabel kLabel;
	private final JTextField kField;
	private final JRadioButton hierPreButton;
	private final JRadioButton randomButton;
	
	public KMeanSettingsPanel(SettingsPanelController controller) {
		this.controller = controller;
		this.kLabel = new JLabel("k-value: ");
		this.kField = new JTextField();
		this.hierPreButton = new JRadioButton("Hier. preclustering");
		this.randomButton = new JRadioButton("Random point preclustering");
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel kPanel = new JPanel();
		kPanel.add(this.kLabel);
		this.kField.setPreferredSize(new Dimension(25,20));
		kPanel.add(this.kField);
		kPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(kPanel);
		
		ButtonGroup group = new ButtonGroup();
		group.add(this.hierPreButton);
		group.add(this.randomButton);
		this.hierPreButton.setSelected(true);
		this.hierPreButton.setEnabled(false);
		this.randomButton.setEnabled(false);
		this.hierPreButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.randomButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(this.hierPreButton);
		this.add(this.randomButton);
	}

	public SettingsPanelController getController() {
		return controller;
	}

	public JLabel getkLabel() {
		return kLabel;
	}

	public JTextField getkField() {
		return kField;
	}

	public JRadioButton getHierPreButton() {
		return hierPreButton;
	}

	public JRadioButton getRandomButton() {
		return randomButton;
	}
	
}
