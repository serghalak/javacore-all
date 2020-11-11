package solidfock.srp.modem_srp_solution;

public interface DataManager {
    void send(String message);
    int receive();
}
