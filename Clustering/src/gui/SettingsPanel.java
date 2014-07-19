package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import controller.SettingsPanelController;
import exception.AbstractGuiException;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -8928464335165056558L;
	
	private final SettingsPanelController controller;
	private final StopConditionPanel conditionPanel;
	private final AlgorithmChoicePanel algoPanel;
	private final PointInputPanel inputPanel;
	private final JButton createAlgo;
	private final KMeanSettingsPanel kPanel;
	private final ClusteringFrame frame;
	
	public SettingsPanel(ClusteringFrame frame) {
		this.frame = frame;
		this.createAlgo = new JButton();
		this.controller = new SettingsPanelController(this);
		this.algoPanel = new AlgorithmChoicePanel(this.controller);
		this.conditionPanel = new StopConditionPanel(this.controller);
		this.inputPanel = new PointInputPanel(this.controller);
		this.kPanel = new KMeanSettingsPanel(this.controller);
		this.initialize();
	}
	
	private void initialize() {
		this.algoPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.add(this.algoPanel);
		
		this.conditionPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.add(this.conditionPanel);
		
		this.kPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.add(this.kPanel);
		
		this.inputPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.add(this.inputPanel);
		
		this.createAlgo.setText("Start clustering");
		this.createAlgo.addActionListener(new MyAlgoButtonListener());
		this.add(this.createAlgo);
		
		this.getController().resetBordersAndErrorLabel();
	}
	
	private class MyAlgoButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				SettingsPanel.this.getController().createAlgo();
			} catch (AbstractGuiException e) {
				SettingsPanel.this.getController().handleException(e);
			}
		}
	}

	public SettingsPanelController getController() {
		return controller;
	}

	public StopConditionPanel getConditionPanel() {
		return conditionPanel;
	}

	public JButton getCreateAlgo() {
		return createAlgo;
	}

	public ClusteringFrame getFrame() {
		return frame;
	}

	public AlgorithmChoicePanel getAlgoPanel() {
		return algoPanel;
	}

	public PointInputPanel getInputPanel() {
		return inputPanel;
	}

	public KMeanSettingsPanel getkPanel() {
		return kPanel;
	}

}
