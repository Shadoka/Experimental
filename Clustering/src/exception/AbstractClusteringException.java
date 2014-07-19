package exception;

public abstract class AbstractClusteringException extends Exception {

	private static final long serialVersionUID = 5105899093950451317L;

	protected AbstractClusteringException(String message) {
		super(message);
	}
}
