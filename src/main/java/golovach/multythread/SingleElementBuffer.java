package golovach.multythread;

public class SingleElementBuffer {

    private Integer elem = null;

    public synchronized void put(Integer elem) {
        while (this.elem != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.elem = elem;
        notifyAll();
    }

    public synchronized Integer get() {
        while (this.elem == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer result = this.elem;
        this.elem = null;
        return result;
    }
}
