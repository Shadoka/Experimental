package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.SettingsPanelController;

public class AlgorithmChoicePanel extends JPanel {

	private static final long serialVersionUID = -42375263994643211L;
	private final JRadioButton hier, means;
	private final JCheckBox verbosePrinting;
	private final SettingsPanelController controller;
	
	public AlgorithmChoicePanel(SettingsPanelController controller) {
		this.hier = new JRadioButton("Hierarchical Clustering");
		this.means = new JRadioButton("K-Means");
		this.verbosePrinting = new JCheckBox("Print calculations");
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
		this.verbosePrinting.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.hier.addActionListener(new MyHierButtonListener());
		this.means.addActionListener(new MyMeansButtonListener());
		this.verbosePrinting.addActionListener(new MyPrintingButtonListener());
		this.add(this.hier);
		this.add(this.means);
		this.add(this.verbosePrinting);
	}

	private class MyHierButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			AlgorithmChoicePanel.this.getController().selectHierButton();
		}
	}
	
	private class MyPrintingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			AlgorithmChoicePanel.this.getController().showPrinting(AlgorithmChoicePanel.this.getVerbosePrinting().isSelected());
			AlgorithmChoicePanel.this.getController().getPanel().getFrame().getPrintPanel().print("I love to derp around.\n" +
					"Really, I mean it!");
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

	public JCheckBox getVerbosePrinting() {
		return verbosePrinting;
	}
	
}
