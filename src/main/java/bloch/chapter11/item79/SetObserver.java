package bloch.chapter11.item79;

import bloch.chapter11.item79.ObservableSet;

// Set obeserver callback interface - Page 266
public interface SetObserver<E> {
    // Invoked when an element is added to the observable set
    void added(ObservableSet<E> set, E element);
}
