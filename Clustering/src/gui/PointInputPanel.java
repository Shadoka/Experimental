package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.SettingsPanelController;

public class PointInputPanel extends JPanel {

	private static final long serialVersionUID = -1766336677362334306L;
	private final SettingsPanelController controller;
	private final JSlider slider;
	private final JLabel number, title;
	private final JButton generateButton;
	private final static int MAX = 1000;
	private final static int MIN = 10;
	private final static int INIT = 20;
	
	public PointInputPanel(SettingsPanelController controller) {
		this.controller = controller;
		this.number = new JLabel("");
		this.title = new JLabel("Amount of random points");
		this.generateButton = new JButton("Generate");
		this.slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.slider.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.slider.addChangeListener(new MySliderListener());
		
		this.number.setText(INIT+"");
		this.number.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.generateButton.addActionListener(new MyGenerateListener());
		
		this.add(this.title);
		this.add(this.slider);
		this.add(this.number);
		this.add(this.generateButton);
	}
	
	private class MyGenerateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			PointInputPanel.this.getController().handleGenerateClick();
		}
	}
	
	private class MySliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent arg0) {
			PointInputPanel.this.getController().handleSliderEvent(arg0);
		}
	}

	public SettingsPanelController getController() {
		return controller;
	}

	public JButton getGenerateButton() {
		return generateButton;
	}

	public JLabel getNumber() {
		return number;
	}

	public JSlider getSlider() {
		return slider;
	}

	public JLabel getTitle() {
		return title;
	}
	
}
