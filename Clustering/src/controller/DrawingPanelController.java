package controller;

import java.awt.Color;
import java.awt.Graphics;

import gui.DrawingPanel;
import observer.ClusterChangedEvent;
import observer.Event;
import observer.EventVisitor;
import observer.NewPointsEvent;
import observer.Observer;
import util.CoordinateSystem;
import util.EuclideanCluster;
import util.Point;

public class DrawingPanelController implements Observer {

	private final DrawingPanel panel;
	private final CoordinateSystem system;
	private EuclideanCluster highlightedCluster;
	private Point highlightedPoint;
	
	public DrawingPanelController(DrawingPanel panel) {
		this.panel = panel;
		this.system = new CoordinateSystem(new Point(200, 200), 400, 400, false);
		this.system.getGraphics().setBackground(Color.WHITE);
		this.system.getGraphics().setColor(Color.BLACK);
	}
	
	public void paint(Graphics g) {
		this.getSystem().setWidth(this.getPanel().getWidth());
		this.getSystem().setHeight(this.getPanel().getHeight());
		this.getSystem().redraw(this.getPanel().getWidth(), this.getPanel().getHeight(), this.getHighlightedCluster(), this.getHighlightedPoint());
		g.drawImage(this.getSystem().getTargetImage(), 0, 0, null);
	}
	
	public void highlightCluster(EuclideanCluster c) {
		this.setHighlightedCluster(c);
		this.getPanel().repaint();
	}
	
	public void highlightPoint(Point p) {
		this.setHighlightedPoint(p);
		this.getPanel().repaint();
	}
	
	public void viewMoved(int deltaX, int deltaY) {
		this.getSystem().setChangeX(deltaX);
		this.getSystem().setChangeY(deltaY);
		this.getPanel().repaint();
	}
	
	public void zoomChange(boolean in) {
		if (in) {
			this.getSystem().setZoomFactor(0.2);
		} else {
			this.getSystem().setZoomFactor(-0.2);
		}
		this.getPanel().repaint();
	}
	
	@Override
	public void update(Event e) {
		e.accept(new EventVisitor() {
			@Override
			public void handle(ClusterChangedEvent e) {
				DrawingPanelController.this.getSystem().setCluster(e.getNewCluster());
				DrawingPanelController.this.getSystem().redraw(DrawingPanelController.this.getSystem().getWidth(),
						DrawingPanelController.this.getSystem().getHeight(), DrawingPanelController.this.getHighlightedCluster(), DrawingPanelController.this.getHighlightedPoint());
				DrawingPanelController.this.getPanel().repaint();
			}
			@Override
			public void handle(NewPointsEvent e) {
				DrawingPanelController.this.getSystem().setPoints(e.getNewPoints());
				DrawingPanelController.this.getSystem().redraw(DrawingPanelController.this.getSystem()
						.getWidth(), DrawingPanelController.this.getSystem().getHeight(), DrawingPanelController.this.getHighlightedCluster(), DrawingPanelController.this.getHighlightedPoint());
				DrawingPanelController.this.getPanel().repaint();
			}
		});
	}

	public DrawingPanel getPanel() {
		return panel;
	}

	public CoordinateSystem getSystem() {
		return system;
	}

	public EuclideanCluster getHighlightedCluster() {
		return highlightedCluster;
	}

	public void setHighlightedCluster(EuclideanCluster highlighted) {
		this.highlightedCluster = highlighted;
	}

	public Point getHighlightedPoint() {
		return highlightedPoint;
	}

	public void setHighlightedPoint(Point highlightedPoint) {
		this.highlightedPoint = highlightedPoint;
	}
}
