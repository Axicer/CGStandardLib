package fr.creativegames.cgstandardlib.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import fr.creativegames.cgstandardlib.listener.event.AbstractEvent;
import fr.creativegames.cgstandardlib.listener.event.EventHandler;

/**
 * Listener class used to invoke all events extended from {@link AbstractEvent}
 * @author Axicer
 *
 */
public class ListenerHandler {
	
	public static Map<String, AbstractListener> listeners;
	
	static{
		listeners = new HashMap<String, AbstractListener>();
	}
	
	/**
	 * Register a listener extended from {@link AbstractListener}
	 * @param id {@link String} id used to get this listener
	 * @param listener listener extended from {@link AbstractListener} to register
	 */
	public static void registerListener(String id, AbstractListener listener){
		if(listeners.containsKey(id)){
			System.out.println("id already registered");
			return;
		}else{
			listeners.put(id, listener);
		}
	}
	/**
	 * Unregister the listener linked by the given id
	 * @param id {@link String} id relative to the listener
	 */
	public static void unregisterListener(String id){
		if(!listeners.containsKey(id)){
			System.out.println("id not found");
			return;
		}else{
			listeners.remove(id);
		}
	}
	
	/**
	 * Invoke all method from registered {@link AbstractListener} that contains only one argument of type {@link AbstractEvent}
	 * @param event {@link AbstractEvent} to invoke
	 */
	public static void invoke(AbstractEvent event){
		for(AbstractListener lis : listeners.values()){
			if(lis.getClass().getMethods().length == 0)continue;
			for(Method method : lis.getClass().getMethods()){
				if(method.getParameterTypes().length != 1  || !method.isAnnotationPresent(EventHandler.class))continue;
				boolean found = false;
				for(Class<?> c : method.getParameterTypes()){
					if(c.isAssignableFrom(event.getClass())){
						found = true;
						break;
					}
				}
				if(found){
					try {
						method.invoke(lis, event);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
