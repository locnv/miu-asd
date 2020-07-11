package counter;

import java.util.ArrayList;
import java.util.List;

public class Counter {

	private int count = 0;

	private List<FrameObserver> frameObservables = new ArrayList<>();

	public void increment() {
		count++;
		notifyCountChange();
	}

	public void decrement() {
		count--;
		notifyCountChange();
	}

	public void addFrame(FrameObserver frame) {
		frameObservables.add(frame);
	}

	private void notifyCountChange() {
		frameObservables.forEach(frame -> frame.setCount(count));
	}

}
