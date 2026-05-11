package horsman14.v2ch04.interruptible;

import module java.base;

/**
 * This program shows how to interrupt a socket channel.
 */
class InterruptibleSocketDemo {
    void main() {
        Thread.ofPlatform().start(new TestServer());
        boolean socket = IO.readln("Socket or channel? (S/C) ").equalsIgnoreCase("S");
        boolean platform = IO.readln("Platform or virtual? (P/V) ").equalsIgnoreCase("P");
        Runnable client = socket ? this::useSocket : this::useChannel;
        Thread clientThread = platform ? Thread.ofPlatform().start(client)
                : Thread.ofVirtual().start(client);

        IO.readln("Hit Enter to interrupt client");
        clientThread.interrupt();
    }

    /**
     * Connects to the test server, using a channel.
     */
    void useChannel() {
        IO.println("Channel:\n");
        try (SocketChannel channel = SocketChannel
                .open(new InetSocketAddress("localhost", 8189));
                Scanner in = new Scanner(channel)) {            
            while (!Thread.currentThread().isInterrupted()) {
                IO.print("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    IO.println(line);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IO.println("Channel closed\n");
        }
    }

    /**
     * Connects to the test server, using a socket.
     */
    void useSocket() {
        IO.println("Socket:\n");
        try (var sock = new Socket("localhost", 8189); 
                Scanner in = new Scanner(sock.getInputStream())) {
            while (!Thread.currentThread().isInterrupted()) {
                IO.print("Reading ");
                if (in.hasNextLine()) {
                    String line = in.nextLine();
                    IO.println(line);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            IO.println("Socket closed\n");
        }
    }

    /**
     * A server that listens to port 8189 and sends numbers to the client, simulating a hanging
     * server after 10 numbers.
     */
    static class TestServer implements Runnable {
        public void run() {
            try (var s = new ServerSocket(8189); Socket incoming = s.accept()) {
                serve(incoming);
            }
            catch (Exception e) {
                e.printStackTrace();
                ;
            }
            finally {
                IO.println("Closing connection\n");
            }
        }

        private void serve(Socket incoming) throws IOException, InterruptedException {
            int counter = 0;
            try (var out = new PrintWriter(incoming.getOutputStream(), true /* autoFlush */)) {
                while (counter < 100) {
                    counter++;
                    if (counter <= 10) out.println(counter);
                    Thread.sleep(100);
                }
            }
        }
    }
}
