package observer;

import java.util.Vector;

public class Observee {

	private final Vector<Observer> observers;

	public Observee() {
		this.observers = new Vector<Observer>();
	}
	
	public void register(Observer o) {
		this.getObservers().add(o);
	}
	
	public void deregister(Observer o) {
		this.getObservers().remove(o);
	}
	
	public void notifyObservers(Event e) {
		for (Observer o: this.getObservers()) {
			o.update(e);
		}
	}
	
	public Vector<Observer> getObservers() {
		return observers;
	}
}
