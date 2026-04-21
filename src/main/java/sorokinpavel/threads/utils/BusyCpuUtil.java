package sorokinpavel.threads.utils;

import java.security.SecureRandom;

/**
 * Утилита искусственной загрузки CPU (busy-wait) на заданное время.
 */
public final class BusyCpuUtil {

    private static final SecureRandom RANDOM = new SecureRandom();

    private BusyCpuUtil() {}

    /**
     * Поток крутится на CPU указанное кол-во миллисекунд
     * @param ms кол-во миллисекунд
     */
    public static void spinOnCpuMillis(long ms) {
        long end = System.nanoTime() + ms * 1_000_000L;
        long acc = 0;
        while (System.nanoTime() < end) {
            // немного бесполезной арифметики, чтобы нагрузить ALU
            acc += (acc * 31 + 7);
        }
        if (acc == 42) System.out.print(""); // не даёт JIT выкинуть цикл
    }

    /**
     * Безопасно спит указанное время
     * При прерывании возвращает флаг, не проглатывая событие.
     * Добавляет случайную задержку
     */
    public static void spinOnCpuMillisWithJitter(long ms) {
        if (ms <= 0) {
            return;
        }
        var jitter = RANDOM.nextLong(ms/2);
        if (RANDOM.nextBoolean()) {
            jitter *= -1;
        }
        spinOnCpuMillis(ms + jitter);
    }
}

