package horsman14.v2ch04.socket;

import module java.base;

/**
 * This program makes a socket connection to the atomic clock in Boulder, Colorado, and prints
 * the time that the server sends.
 */
class SocketDemo {
    void main() throws Exception {
        try (var s = new Socket("time-a.nist.gov", 13);
                var in = new Scanner(s.getInputStream())) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                IO.println(line);
            }
        }
    }
}
