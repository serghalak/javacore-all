package horsman14.v2ch04.server;

import module java.base;

/**
 * This program implements a simple server that listens to port 8189 or a port given as
 * argument, and echoes back all client input.
 */
class EchoServer {
    void main(String[] args) throws Exception {
        int port = args.length >= 1 ? Integer.parseInt(args[0]) : 8189;

        try (var s = new ServerSocket(port); // establish server socket
                Socket incoming = s.accept()) { // wait for client connection
            serve(incoming);
        }
    }

    void serve(Socket incoming) throws IOException {
        try (var in = new Scanner(incoming.getInputStream());
                var out = new PrintWriter(incoming.getOutputStream(), true /* autoFlush */)) {
            out.println("Hello! Enter BYE to exit.");

            // echo client input
            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.strip().equals("BYE")) done = true;
            }
        }
    }
}
