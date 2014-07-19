package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.DrawingPanelController;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 4693107400480860483L;
	private final ClusteringFrame frame;
	private final DrawingPanelController controller;
	
	public DrawingPanel(ClusteringFrame frame, int width, int height) {
		this.frame = frame;
		this.setPreferredSize(new Dimension(width, height));
		this.controller = new DrawingPanelController(this);
	}
	
	@Override
	public void paint(Graphics g) {
		this.getController().paint(g);
	}

	public ClusteringFrame getFrame() {
		return frame;
	}

	public DrawingPanelController getController() {
		return controller;
	}
	
}
