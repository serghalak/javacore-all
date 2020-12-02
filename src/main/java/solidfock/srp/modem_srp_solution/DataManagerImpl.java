package solidfock.srp.modem_srp_solution;

public class DataManagerImpl implements DataManager {
    @Override
    public void send(String message) {
        System.out.println("data sent successfully");
    }

    @Override
    public int receive() {
        System.out.println("data received successfully");
        return 0;
    }
}
