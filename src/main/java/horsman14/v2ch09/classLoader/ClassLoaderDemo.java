package horsman14.v2ch09.classLoader;

import module java.base;

/**
 * This program demonstrates a custom class loader that decrypts class files.
 */
class ClassLoaderDemo {
    void main(String[] args) throws Exception {
        String className = IO.readln("Class name: ");
        int decryptionKey = Integer.parseInt(IO.readln("Decryption key: "));
        var loader = new CryptoClassLoader(decryptionKey);
        Class<?> c = loader.loadClass(className);
        Method m = c.getMethod("main", String[].class);
        m.invoke(null, (Object) new String[] {});
    }
}

/**
 * This class loader loads encrypted class files.
 */
class CryptoClassLoader extends ClassLoader {
    private int key;

    /**
     * Constructs a crypto class loader.
     * @param k the decryption key
     */
    public CryptoClassLoader(int k) {
        key = k;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = loadClassBytes(name);
            Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
            if (cl == null) throw new ClassNotFoundException(name);
            return cl;
        }
        catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    /**
     * Loads and decrypt the class file bytes.
     * @param name the class name
     * @return an array with the class file bytes
     */
    private byte[] loadClassBytes(String name) throws IOException {
        String cname = name.replace(".", "/") + ".caesar";
        // Read encrypted class files from the same directory as this class loader
        byte[] bytes = getClass().getResourceAsStream(cname).readAllBytes();
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (byte) (bytes[i] - key);
        return bytes;
    }
}
