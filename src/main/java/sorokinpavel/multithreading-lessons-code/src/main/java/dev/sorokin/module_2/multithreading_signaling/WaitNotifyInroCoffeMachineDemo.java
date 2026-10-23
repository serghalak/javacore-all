package dev.sorokin.module_2.multithreading_signaling;

import dev.sorokin.utils.Logger;
import dev.sorokin.utils.ThreadSleepUtil;

public class WaitNotifyInroCoffeMachineDemo {

    static class CoffeeMachine {
        private boolean coffeeReady = false;

        public synchronized void makeCoffee() {
            while (coffeeReady) { // пока чашка не забрана — ждём
                try {
                    this.wait();
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            Logger.log("Машина варит кофе...");
            ThreadSleepUtil.safeSleepWithoutThrow(1000);
            coffeeReady = true;
            Logger.log("Кофе готов! Можно забирать.");
            this.notify(); // разбудим потребителя
        }

        public synchronized void takeCoffee() {
            while (!coffeeReady) { // ждём, пока кофе приготовится
                try { 
                    Logger.log("Жду, когда кофе будет готов...");
                    this.wait();
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            Logger.log("Забрал кофе и пошёл пить!");
            coffeeReady = false;
            this.notify(); // разбудим производителя
        }

        public static synchronized int getResult() {
            return 0;
        }
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();

        Thread barista = new Thread(machine::makeCoffee, "Barista");
        Thread customer = new Thread(machine::takeCoffee, "Customer-1");
        Thread customer2 = new Thread(machine::takeCoffee, "Customer-2");

        customer.start();
        ThreadSleepUtil.safeSleepWithoutThrow(500);
        customer2.start();
        ThreadSleepUtil.safeSleepWithoutThrow(500);
        barista.start();
    }
}
