import java.util.ArrayList;
import java.util.List;


/***********
 * The VideoSink class represents the entry point for high level
 * analysis of videos. Images are fed to the VideoSink class via 
 * the receiveFrame. The videoSink has the ability to display 
 * images with the ImageViewer class.
 * 
 * @author Sam Epstein
 **********/
public class VideoSink implements Sink<CS440Image>, Source<CS440Image> {

	//subscribers
	private List<Sink<CS440Image>> subscribers = new ArrayList<Sink<CS440Image>>(1);
	
	//Simple counter for video cutoff
	long counter;
	
	//The constructor initializes the window for display
	VideoSink()
	{
		counter = 0;
	}
	
	/* (non-Javadoc)
	 * @see ImageSink#receive(CS440Image)
	 */
	@Override
	public void receive(CS440Image frame) {
	}

	/* (non-Javadoc)
	 * @see Source#subscribe(Sink)
	 */
	@Override
	public void subscribe(Sink<CS440Image> sink) {
		subscribers.add(sink);
	}
	
	/* (non-Javadoc)
	 * @see ImageSink#receiveFrame(CS440Image)
	 */
	public boolean receiveFrame(CS440Image frame, ObjectTracker ot) {
		for (Sink<CS440Image> subscriber : subscribers) {
			subscriber.receive(frame);	
		}
		return true;
	}

	/***
	 * Closes the window
	 */
	public void close()
	{
	}

}
