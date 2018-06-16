package com.company.company.service;

import com.company.company.util.NotNullByDefault;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NotNullByDefault
public class NotificationEventListener {
    Map<String, Set<EventListener>> listeners = new HashMap<>();

    public NotificationEventListener(String ... events) {
        for (String event : events) {
            this.listeners.put(event, new HashSet<>());
        }
    }


    public void subscribe(String eventType, EventListener listener) {
        listeners.get(eventType).add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        listeners.get(eventType).remove(listener);
    }

    public void notify(String eventType, File file) {
        for (EventListener listener : listeners.get(eventType)) {
            listener.update(eventType, file);
        }
    }
}
