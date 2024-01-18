package Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    private ConcurrentHashMap<Class<?>, CopyOnWriteArrayList<EventHandler<?>>> subscribersMap;

    public EventBus() {
        subscribersMap = new ConcurrentHashMap<>();
    }

    public <E> void subscribe(Class<E> eventType, EventHandler<E> handler) {
        subscribersMap.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(handler);
    }

    public <E> void unsubscribe(Class<E> eventType, EventHandler<E> handler) {
        subscribersMap.getOrDefault(eventType, new CopyOnWriteArrayList<>()).remove(handler);
    }

    public void publish(Object event) {
        Class<?> eventType = event.getClass();
        CopyOnWriteArrayList<EventHandler<?>> subscribers = subscribersMap.get(eventType);

        if (subscribers != null) {
            for (EventHandler<?> handler : subscribers) {
                try {
                    @SuppressWarnings("unchecked")
                    EventHandler<Object> objHandler = (EventHandler<Object>) handler;
                    objHandler.handle(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FunctionalInterface
    public interface EventHandler<E> {
        void handle(E event);
    }
}
