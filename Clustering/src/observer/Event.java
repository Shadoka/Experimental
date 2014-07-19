package observer;

public interface Event {

	public void accept(EventVisitor v);
}
