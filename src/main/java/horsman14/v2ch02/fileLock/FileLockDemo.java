package horsman14.v2ch02.fileLock;

import module java.base;

/**
 * This program writes user names and passwords to the file passwords.properties. The file is
 * atomically updated. With Linux, try 
 * for f in $(seq 1 100) ; do (java fileLock.FileLockDemo user$f secret$f &) ; done 
 * When all Java processes have finished, then passwords.properties
 * has an entry for each of them.
 */

public class FileLockDemo {
    void main(String[] args) throws Exception {
        Path path = Path.of("password.properties");
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        try (FileLock _ = channel.lock()) {
            // Read bytes from file
            ByteBuffer readBuffer = ByteBuffer.allocate((int) channel.size());
            channel.read(readBuffer);

            // Read props from bytes
            Reader in = new StringReader(new String(readBuffer.array()));
            Properties props = new Properties();
            props.load(in);

            // Get username, password from args or user input
            String username;
            char[] password;
            if (args.length >= 2) {
                username = args[0];
                password = args[1].toCharArray();
            }
            else {
                Console console = System.console();
                username = console.readLine("User name: ");
                password = console.readPassword("Password: ");
            }

            // Hash password
            final int ITERATIONS = 210_000;
            final int SALT_BYTES = 16;
            final int HASH_BYTES = 32;
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_BYTES];
            random.nextBytes(salt);

            var spec = new PBEKeySpec(password, salt, ITERATIONS, 8 * HASH_BYTES);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            Base64.Encoder encoder = Base64.getEncoder();

            // Put username and hashed password into props
            props.put(username, ITERATIONS + "|" + encoder.encodeToString(salt) + "|"
                    + encoder.encodeToString(hash));

            // Write props to bytes
            StringWriter out = new StringWriter();
            props.store(out, null);
            byte[] outBytes = out.toString().getBytes();

            // Write bytes to file
            channel.truncate(0);
            ByteBuffer writeBuffer = ByteBuffer.wrap(outBytes);
            channel.write(writeBuffer);
        }
    }
}
