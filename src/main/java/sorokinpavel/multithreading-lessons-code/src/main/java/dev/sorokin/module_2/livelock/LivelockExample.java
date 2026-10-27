package dev.sorokin.module_2.livelock;

import dev.sorokin.utils.ThreadSleepUtil;

public class LivelockExample {

    static class Spoon {
        private volatile Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public synchronized void setOwner(Diner diner) {
            this.owner = diner;
        }

        public synchronized void use() {
            System.out.println(owner.name + " ест!");
        }
    }

    static class Diner {
        private final String name;
        private boolean isHungry;

        public Diner(String name, boolean hungry) {
            this.name = name;
            this.isHungry = hungry;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                // Если ложка не у меня — жду
                if (spoon.getOwner() != this) {
                    ThreadSleepUtil.safeSleepWithoutThrow(10);
                    continue;
                }

//                // Если другой голоден — уступаю
//                if (spouse.isHungry) {
//                    System.out.println(name + ": мой(я) " + spouse.name + " голоден, отдаю ложку...");
//                    spoon.setOwner(spouse);
//                    continue;
//                }

                // Ем (сюда никогда никто не дойдет)
                spoon.use();
                isHungry = false;
                System.out.println(name + ": я поел!");
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {
        Diner husband = new Diner("Муж", true);
        Diner wife = new Diner("Жена", true);

        Spoon spoon = new Spoon(husband);

        Thread t1 = new Thread(() -> husband.eatWith(spoon, wife));
        Thread t2 = new Thread(() -> wife.eatWith(spoon, husband));

        t1.start();
        t2.start();
    }
}
