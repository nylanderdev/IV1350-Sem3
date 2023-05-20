package iv1350;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An obersevable container for a state of type T
 * @param <T> the type of state
 */
public class Observer<T> {
    private T state;
    private Set<Consumer<T>> listeners = new LinkedHashSet<>();
    public T get() {
        return state;
    }
    public void update(T newState) {
        state = newState;
        for (Consumer<T> listener : listeners) {
            listener.accept(state);
        }
    }
    public void addListener(Consumer<T> listener) {
        listeners.add(listener);
    }
}
