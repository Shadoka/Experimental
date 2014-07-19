package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SettingsPanelController;

public class RangePanel extends JPanel {

	private static final long serialVersionUID = 7699862739543310971L;
	private final SettingsPanelController controller;
	private final JLabel rangeLbl, toLbl;
	private final JTextField min, max;
	
	public RangePanel(SettingsPanelController controller, String axis) {
		this.controller = controller;
		this.rangeLbl = new JLabel("Range " + axis + ": ");
		this.toLbl = new JLabel("to");
		this.min = new JTextField();
		this.max = new JTextField();
		this.initialize();
	}
	
	private void initialize() {
		this.min.setText(0 + "");
		this.max.setText(100 + "");
		this.min.setPreferredSize(new Dimension(25, 20));
		this.max.setPreferredSize(new Dimension(25, 20));
		this.min.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.max.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(this.rangeLbl);
		this.add(this.min);
		this.add(this.toLbl);
		this.add(this.max);
	}

	public SettingsPanelController getController() {
		return controller;
	}

	public JLabel getRangeLbl() {
		return rangeLbl;
	}

	public JLabel getToLbl() {
		return toLbl;
	}

	public JTextField getMax() {
		return max;
	}

	public JTextField getMin() {
		return min;
	}
	
}
