package horsman14.v2ch04.ssl;

import module java.base;

/**
 * This program connects to a server via SSL and echoes the server responses.
 */
class EchoClient {
    void main(String[] args) throws Exception {
        int port = args.length >= 1 ? Integer.parseInt(args[0]) : 8189;
        SocketFactory factory = SSLSocketFactory.getDefault();
        String message = """
Hello
World
BYE
""";
        try (Socket s = factory.createSocket(InetAddress.getLocalHost(), port);
                OutputStream os = s.getOutputStream();
                var in = new Scanner(s.getInputStream())) {
            os.write(message.getBytes());
            os.flush();
            boolean done = false;
            while (!done) {
                String line = in.nextLine();
                System.out.println(line);
                if (line.equals("Echo: BYE")) done = true;
            }
        }
    }
}
