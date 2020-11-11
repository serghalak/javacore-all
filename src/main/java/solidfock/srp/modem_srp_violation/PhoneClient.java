package solidfock.srp.modem_srp_violation;

public class PhoneClient {
    public static void main(String[] args) {
        Phone phone=new PhoneImpl();
        phone.dial("0123");
        phone.send("message text");
        phone.receive();
        phone.disconnect();
    }
}
