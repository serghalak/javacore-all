package horsman14.v2ch09.aes;

import module java.base;

/**
 * This program tests the AES cipher. It can generate a secret key,
 * or encrypt or decrypt a file.
 */
class AESDemo {
    void main(String[] args) throws Exception {
        if (args[0].equals("-genkey")) {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            var random = new SecureRandom();
            keygen.init(random);
            SecretKey key = keygen.generateKey();
            try (var out = new ObjectOutputStream(new FileOutputStream(args[1]))) {
                out.writeObject(key);
            }
        }
        else {
            int mode;
            if (args[0].equals("-encrypt"))
                mode = Cipher.ENCRYPT_MODE;
            else
                mode = Cipher.DECRYPT_MODE;

            try (var keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                    var in = new FileInputStream(args[1]);
                    var out = new FileOutputStream(args[2])) {
                var key = (Key) keyIn.readObject();
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(mode, key);
                Util.crypt(in, out, cipher);
            }
        }
    }
}
