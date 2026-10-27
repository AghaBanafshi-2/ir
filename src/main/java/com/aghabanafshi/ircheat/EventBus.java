package com.aghabanafshi.ircheat.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<Object>>> listeners = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void subscribe(Class<T> type, Consumer<T> listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add((Consumer<Object>) listener);
    }

    public <T> void post(T event) {
        List<Consumer<Object>> list = listeners.get(event.getClass());
        if (list != null) list.forEach(c -> c.accept(event));
    }
                              }
