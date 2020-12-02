package solidfock.srp.modem_srp_solution;

public class Phone implements ConnectionManager,DataManager {
    private ConnectionManager connectionManager;
    private  DataManager dataManager;

    public Phone(ConnectionManager connectionManager, DataManager dataManager) {
        this.connectionManager = connectionManager;
        this.dataManager = dataManager;
    }


    @Override
    public void dial(String phonenumber) {
        connectionManager.dial(phonenumber);
    }

    @Override
    public void disconnect() {
        connectionManager.disconnect();
    }

    @Override
    public void send(String message) {
        dataManager.send(message);

    }

    @Override
    public int receive() {
        return dataManager.receive();
    }
}
