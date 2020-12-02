package solidfock.srp.modem_srp_solution;

public class ConnectionManagerImpl implements ConnectionManager {
    @Override
    public void dial(String phonenumber) {
        System.out.println("connected established");
    }

    @Override
    public void disconnect() {
        System.out.println("disconnected");
    }
}
