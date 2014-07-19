package controller;

import gui.ClusterOverviewPanel;

import java.util.Vector;

import observer.ClusterChangedEvent;
import observer.Event;
import observer.EventVisitor;
import observer.NewPointsEvent;
import observer.Observer;

import util.EuclideanCluster;
import util.Point;

public class ClusterPanelController implements Observer {

	private final ClusterOverviewPanel panel;
	
	public ClusterPanelController(ClusterOverviewPanel panel) {
		this.panel = panel;
	}
	
	public void updatePointList(EuclideanCluster cluster) {
		Vector<Point> newPoints = new Vector<>();
		for (Point p : cluster.getPoints()) {
			newPoints.add(p);
		}
		this.getPanel().getPointPanel().getPointList().setListData(newPoints);
	}
	
	public void updateClusterList(Vector<EuclideanCluster> newClusters) {
		this.getPanel().getClusterPanel().getClusterList().clearSelection();
		this.getPanel().getPointPanel().getPointList().clearSelection();
		Vector<Point> empty = new Vector<>();
		this.getPanel().getPointPanel().getPointList().setListData(empty);
		this.getPanel().getClusterPanel().getClusterList().setListData(newClusters);
	}
	
	public void highlightCluster(EuclideanCluster c) {
		this.getPanel().getFrame().getDrawingPanel().getController().highlightCluster(c);
	}
	
	public void highlightPoint(Point p) {
		this.getPanel().getFrame().getDrawingPanel().getController().highlightPoint(p);
	}
	
	public ClusterOverviewPanel getPanel() {
		return panel;
	}

	@Override
	public void update(Event e) {
		e.accept(new EventVisitor() {
			@Override
			public void handle(ClusterChangedEvent e) {
				ClusterPanelController.this.updateClusterList(e.getNewCluster());
			}
			@Override
			public void handle(NewPointsEvent e) {
				// Nix
			}
		});
	}

}
