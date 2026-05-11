package horsman14.v1ch06.serviceLoader;

import module java.base;

class ServiceLoaderDemo {
    public static ServiceLoader<Cipher> cipherLoader
            = ServiceLoader.load(Cipher.class);

    void main() throws Exception {
        Cipher cipher = getCipher(1);
        String message = "Meet me at the toga party.";
        byte[] bytes = cipher.encrypt(message.getBytes(), new byte[]{ 3 });
        var encrypted = new String(bytes);
        IO.println(encrypted);
    }

    public static Cipher getCipher(int minStrength) {
        for (Cipher cipher : cipherLoader)
            // Implicitly calls iterator
            if (cipher.strength() >= minStrength) return cipher;
        return null;
    }
}
