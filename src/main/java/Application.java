import network.ReceiverThread;
import network.SenderThread;

public class Application {

    public static void main(String[] args) {

        SenderThread sender = new SenderThread();
        ReceiverThread receiver = new ReceiverThread();

        new Thread(sender).start();
        new Thread(receiver).start();

    }

}
