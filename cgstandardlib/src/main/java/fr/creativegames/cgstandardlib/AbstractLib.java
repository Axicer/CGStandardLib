package fr.creativegames.cgstandardlib;

import fr.creativegames.cgstandardlib.listener.AbstractListener;
import fr.creativegames.cgstandardlib.listener.ListenerHandler;

/**
 * Represent an abstract Library
 * @author Axicer
 *
 * @param <T> {@link AbstractListener} main listener linked to this {@link AbstractLib} 
 */
public abstract class AbstractLib<T extends AbstractListener> {
	
	private String name;
	private boolean started;
	protected T listener;
	
	/**
	 * Constructor of an abstract library
	 * @param name {@link String} name of the library
	 * @param started {@link Boolean} check if library should invoke {@code start()} method
	 */
	public AbstractLib(String name, boolean started) {
		this.name = name;
		if(started) start();
	}
	
	/**
	 * Get library name
	 * @return {@link String} library's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set name of this library
	 * @param name {@link String} library's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get {@link AbstractListener} linked to this {@link AbstractLib}
	 * @return {@link AbstractListener} linked
	 */
	public T getListener() {
		return listener;
	}
	/**
	 * Set the {@link AbstractListener} linked to this {@link AbstractLib}
	 * @param listener {@link AbstractListener} to link
	 * @param register {@link Boolean} check if the listener should be registered now
	 */
	protected void setListener(T listener, boolean register) {
		if(register){
			if(this.listener != null){
				ListenerHandler.unregisterListener(this.listener.getID());
			}
			this.listener = listener;
			ListenerHandler.registerListener(listener.getID(), listener);
		}else{
			this.listener = listener;
		}
	}

	/**
	 * default start method
	 */
	public void start(){
		started = true;
	}
	/**
	 * default stop method
	 */
	public void stop(){
		started = false;
	}
	/**
	 * get whether the library is started
	 * @return {@link Boolean} library is started
	 */
	public boolean isStarted(){
		return this.started;
	}
}
