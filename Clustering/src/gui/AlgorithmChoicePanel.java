package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.SettingsPanelController;

public class AlgorithmChoicePanel extends JPanel {

	private static final long serialVersionUID = -42375263994643211L;
	private final JRadioButton hier, means;
	private final SettingsPanelController controller;
	
	public AlgorithmChoicePanel(SettingsPanelController controller) {
		this.hier = new JRadioButton("Hierarchical Clustering");
		this.means = new JRadioButton("K-Means");
		this.controller = controller;
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		ButtonGroup group = new ButtonGroup();
		group.add(this.hier);
		group.add(this.means);
		this.hier.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.means.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.hier.addActionListener(new MyHierButtonListener());
		this.means.addActionListener(new MyMeansButtonListener());
		this.add(this.hier);
		this.add(this.means);
	}

	private class MyHierButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			AlgorithmChoicePanel.this.getController().selectHierButton();
		}
	}
	
	private class MyMeansButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			AlgorithmChoicePanel.this.getController().selectMeansButton();
		}
	}
	
	public JRadioButton getHier() {
		return hier;
	}

	public JRadioButton getMeans() {
		return means;
	}

	public SettingsPanelController getController() {
		return controller;
	}
	
}
