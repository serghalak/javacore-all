package solidfock.srp.modem_srp_violation;

public interface Phone {

    void dial(String phonenumber);
    void disconnect();
    void send(String message);
    int receive();
}
