/**
 * A generic interface for objects which can be notified of {@link CS440Image
 * CS440Images}.
 * 
 * @author dml
 * 
 */
public interface ImageSink<T> {

	/**
	 * Notify this {@link ImageSink} of a new {@link CS440Image}.
	 * 
	 * @param frame
	 *            - the new {@link CS440Image}
	 */
	void receive(T U);
}