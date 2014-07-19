package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import util.Point;

import controller.ClusterPanelController;

public class PointPanel extends JPanel {

	private static final long serialVersionUID = 7773186604985384103L;
	private final ClusterPanelController controller;
	private final JLabel headLabel;
	private JList<Point> pointList;
	private JScrollPane scrollPanel;
	
	public PointPanel(ClusterPanelController controller) {
		this.controller = controller;
		this.headLabel = new JLabel("Points: ");
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(new BorderLayout());
		
		this.add(this.headLabel, BorderLayout.NORTH);
		
		this.pointList = new JList<>();
		this.pointList.setModel(new DefaultListModel<Point>());
		this.scrollPanel = new JScrollPane(this.pointList);
		this.scrollPanel.setPreferredSize(new Dimension(this.getWidth() + 90, this.getHeight()));
		this.add(this.scrollPanel, BorderLayout.CENTER);
		
		this.pointList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Point selected = PointPanel.this.getPointList().getSelectedValue();
				if (selected != null) {
					PointPanel.this.getController().highlightPoint(selected);
				}
			}
		});
		
		this.setVisible(true);
	}
	
	public ClusterPanelController getController() {
		return controller;
	}

	public JLabel getHeadLabel() {
		return headLabel;
	}

	public JList<Point> getPointList() {
		return pointList;
	}

	public void setPointList(JList<Point> pointList) {
		this.pointList = pointList;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}
	
}
