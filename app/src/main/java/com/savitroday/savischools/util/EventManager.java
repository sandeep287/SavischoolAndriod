package com.savitroday.savischools.util;

/**
 * Created by Harshita Ahuja on 15/07/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {

    static final private HashMap<Integer, ArrayList<Object>> observers = new HashMap<Integer, ArrayList<Object>>();

    static final private HashMap<Integer, Object> removeAfterBroadcast = new HashMap<Integer, Object>();
    static final private HashMap<Integer, Object> addAfterBroadcast = new HashMap<>();
    private static volatile EventManager Instance = null;
    private boolean broadcasting = false;

    public static EventManager getInstance() {
        EventManager localInstance = Instance;
        if (localInstance == null) {
            synchronized (EventManager.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new EventManager();
                }
            }
        }
        return localInstance;
    }

    /**
     * Deallocates the resources and Instance of Event center
     */
    public static void cleanup() {
        observers.clear();
        removeAfterBroadcast.clear();
        addAfterBroadcast.clear();
        //Instance = null;
    }

    public void postEventName(int id, Object... args) {
        synchronized (observers) {
            broadcasting = true;
            ArrayList<Object> objects = observers.get(id);
            if (objects != null) {
                for (Object obj : objects) {
                    ((EventManagerDelegate) obj).didReceivedEvent(id, args);
                }
            }
            broadcasting = false;
            if (!removeAfterBroadcast.isEmpty()) {
                for (HashMap.Entry<Integer, Object> entry : removeAfterBroadcast.entrySet()) {
                    removeObserver(entry.getValue(), entry.getKey());
                }
                removeAfterBroadcast.clear();
            }
            if (!addAfterBroadcast.isEmpty()) {
                for (HashMap.Entry<Integer, Object> entry : addAfterBroadcast.entrySet()) {
                    addObserver(entry.getValue(), entry.getKey());
                }
                addAfterBroadcast.clear();
            }
        }
    }

    public void addObserver(Object observer, int id) {
        synchronized (observers) {
            if (broadcasting) {
                addAfterBroadcast.put(id, observer);
                return;
            }
            ArrayList<Object> objects = observers.get(id);
            if (objects == null) {
                observers.put(id, (objects = new ArrayList<Object>()));
            }
            if (objects.contains(observer)) {
                return;
            }
            objects.add(observer);
        }
    }

    public void removeObserver(Object observer, int id) {
        synchronized (observers) {
            if (broadcasting) {
                removeAfterBroadcast.put(id, observer);
                return;
            }
            ArrayList<Object> objects = observers.get(id);
            if (objects != null) {
                objects.remove(observer);
                if (objects.size() == 0) {
                    observers.remove(id);
                }
            }
        }
    }

    public interface EventManagerDelegate {
        void didReceivedEvent(int id, Object... args);
    }

}

