package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.ClusterPanelController;

public class ClusterOverviewPanel extends JPanel {

	private static final long serialVersionUID = 3591340177840139534L;
	private final ClusterPanelController controller;
	private ClusterPanel clusterPanel;
	private PointPanel pointPanel;
	private final ClusteringFrame frame;
	
	public ClusterOverviewPanel(ClusteringFrame frame) {
		this.controller = new ClusterPanelController(this);
		this.frame = frame;
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(new BorderLayout());
		
		this.clusterPanel = new ClusterPanel(this.getController());
		this.clusterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(this.clusterPanel, BorderLayout.WEST);
		
		this.pointPanel = new PointPanel(this.getController());
		this.pointPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(this.pointPanel, BorderLayout.EAST);
		
		this.setPreferredSize(new Dimension(this.getWidth() + 140, this.getHeight()));
		
		this.setVisible(true);
	}

	public ClusterPanelController getController() {
		return controller;
	}

	public ClusterPanel getClusterPanel() {
		return clusterPanel;
	}

	public void setClusterPanel(ClusterPanel clusterPanel) {
		this.clusterPanel = clusterPanel;
	}

	public PointPanel getPointPanel() {
		return pointPanel;
	}

	public void setPointPanel(PointPanel pointPanel) {
		this.pointPanel = pointPanel;
	}

	public ClusteringFrame getFrame() {
		return frame;
	}
}
