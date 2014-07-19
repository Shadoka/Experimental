package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.SettingsPanelController;

public class StopConditionPanel extends JPanel {

	private static final long serialVersionUID = -460937473917467708L;
	private final SettingsPanelController controller;
	private final JRadioButton amountLimit, radiusLimit, diameterLimit;
	private final JTextField amount, radius, diameter;
	
	public StopConditionPanel(SettingsPanelController controller) {
		this.controller = controller;
		this.amountLimit = new JRadioButton("Cluster-Limit");
		this.amount = new JTextField();
		this.radiusLimit = new JRadioButton("Radius-Limit");
		this.radius = new JTextField();
		this.diameterLimit = new JRadioButton("Diameter-Limit");
		this.diameter = new JTextField();
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		
		this.amountLimit.setEnabled(false);
		this.amountLimit.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.amountLimit.addActionListener(new MyAmountLimitListener());
		this.radiusLimit.setEnabled(false);
		this.radiusLimit.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.radiusLimit.addActionListener(new MyRadiusLimitListener());
		this.diameterLimit.setEnabled(false);
		this.diameterLimit.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.diameterLimit.addActionListener(new MyDiameterLimitListener());
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(this.amountLimit);
		btnGroup.add(this.radiusLimit);
		btnGroup.add(this.diameterLimit);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(this.amountLimit);
		buttonPanel.add(this.radiusLimit);
		buttonPanel.add(this.diameterLimit);
		this.add(buttonPanel, BorderLayout.WEST);
		
		JPanel fieldPanel = new JPanel();
		
		this.amount.setEnabled(false);
		this.amount.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.amount.setPreferredSize(new Dimension(25,10));
		this.radius.setEnabled(false);
		this.radius.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.radius.setPreferredSize(new Dimension(25,10));
		this.diameter.setEnabled(false);
		this.diameter.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.diameter.setPreferredSize(new Dimension(25,10));
		
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
		fieldPanel.add(this.amount);
		fieldPanel.add(this.radius);
		fieldPanel.add(this.diameter);
		this.add(fieldPanel, BorderLayout.EAST);
		
		this.setVisible(true);
	}
	
	private class MyAmountLimitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			StopConditionPanel.this.getController().selectAmountLimit();
		}
	}
	
	private class MyRadiusLimitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			StopConditionPanel.this.getController().selectRadiusLimit();
		}
	}

	private class MyDiameterLimitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			StopConditionPanel.this.getController().selectDiameterLimit();
		}
	}
	
	public SettingsPanelController getController() {
		return controller;
	}

	public JRadioButton getAmountLimit() {
		return amountLimit;
	}

	public JRadioButton getRadiusLimit() {
		return radiusLimit;
	}

	public JRadioButton getDiameterLimit() {
		return diameterLimit;
	}

	public JTextField getAmount() {
		return amount;
	}

	public JTextField getRadius() {
		return radius;
	}

	public JTextField getDiameter() {
		return diameter;
	}
	
}
