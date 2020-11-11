package solidfock.srp.modem_srp_solution;

public class PhoneClient {
    public static void main(String[] args) {
        Phone phone=new Phone(new ConnectionManagerImpl(),new DataManagerImpl());
        phone.dial("025585");
        phone.send("message text");
        phone.receive();
        phone.disconnect();
    }
}
