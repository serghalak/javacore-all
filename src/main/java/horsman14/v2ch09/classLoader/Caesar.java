package horsman14.v2ch09.classLoader;

import module java.base;

/**
 * Encrypts or decrypts a file using the Caesar cipher. Supply the input file, output file,
 * and the encryption key (or its negative for decryption) on the command line.
 */
class Caesar {
    void main(String[] args) throws Exception {
        try (var in = new FileInputStream(args[0]); var out = new FileOutputStream(args[1])) {
            int key = Integer.parseInt(args[2]);
            boolean done = false;
            while (!done) {
                int ch = in.read();
                if (ch == -1)
                    done = true;
                else
                    out.write((byte) (ch + key));
            }
        }
    }
}
