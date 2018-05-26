package jpu206.javapetri.ihm;

import java.util.Observable;

public interface Observer {
	public void update();

	public void update(Observable observable, Object arg);

}
