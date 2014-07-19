package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.EuclideanCluster;
import util.Point;

import controller.ClusterPanelController;

public class ClusterPanel extends JPanel {

	private static final long serialVersionUID = 3591340177840139534L;
	private final ClusterPanelController controller;
	private final JLabel headLabel;
	private final JList<EuclideanCluster> clusterList;
	private JScrollPane scrollPanel;
	
	public ClusterPanel(ClusterPanelController controller) {
		this.controller = controller;
		this.headLabel = new JLabel("Cluster: ");
		this.clusterList = new JList<EuclideanCluster>();
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.add(this.headLabel, BorderLayout.NORTH);
		
		this.clusterList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		this.clusterList.setModel(new DefaultListModel<EuclideanCluster>());
		this.clusterList.addListSelectionListener(new MyClusterSelectionListener());
		this.scrollPanel = new JScrollPane(this.clusterList);
		this.scrollPanel.setPreferredSize(new Dimension(this.getWidth() + 40, this.getHeight()));
		this.add(this.scrollPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void testFill() {
		EuclideanCluster cluster = new EuclideanCluster();
		Vector<Point> points = new Vector<>();
		points.add(new Point(2,2));
		points.add(new Point(4,3));
		points.add(new Point(6,4));
		cluster.setPoints(points);
		Vector<EuclideanCluster> newClusters = new Vector<>();
		newClusters.add(cluster);
		this.getController().updateClusterList(newClusters);
	}
	
	private class MyClusterSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			EuclideanCluster selected = ClusterPanel.this.getClusterList().getSelectedValue();
			// selectionClear --> valueChanged --> grrrrrr
			if (selected != null) {
				ClusterPanel.this.getController().updatePointList(selected);
				ClusterPanel.this.getController().highlightCluster(selected);
			}
		}
	}

	public ClusterPanelController getController() {
		return controller;
	}

	public JLabel getHeadLabel() {
		return headLabel;
	}

	public JList<EuclideanCluster> getClusterList() {
		return clusterList;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}
	
}
