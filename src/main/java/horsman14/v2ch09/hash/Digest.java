package horsman14.v2ch09.hash;

import module java.base;

/**
 * This program computes the message digest of a file.
 */
class Digest {
    /**
     * @param args args[0] is the filename, args[1] is optionally the algorithm 
     * (SHA-1, SHA-256, or MD5)
     */
    void main(String[] args) throws Exception {
        String filename = args.length >= 1 ? args[0] : IO.readln("File name: ");
        String algname;
        if (args.length >= 2)
            algname = args[1];
        else {
            IO.println("Select one of the following algorithms: ");
            for (Provider p : Security.getProviders())
                for (Provider.Service s : p.getServices())
                    if (s.getType().equals("MessageDigest")) IO.println(s.getAlgorithm());
            algname = IO.readln("Algorithm: ");
        }
        MessageDigest alg = MessageDigest.getInstance(algname);
        byte[] input = Files.readAllBytes(Path.of(filename));
        byte[] hash = alg.digest(input);
        for (byte h : hash)
            IO.print("%02X ".formatted(h & 0xFF));
        IO.println();
    }
}
