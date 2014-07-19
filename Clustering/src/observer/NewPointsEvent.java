package observer;

import java.util.Vector;

import util.Point;

public class NewPointsEvent implements Event {

	private final Vector<Point> newPoints;
	
	public NewPointsEvent(Vector<Point> newPoints) {
		this.newPoints = newPoints;
	}
	
	@Override
	public void accept(EventVisitor v) {
		v.handle(this);
	}

	public Vector<Point> getNewPoints() {
		return newPoints;
	}

}
