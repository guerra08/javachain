package network;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import utils.ResourceLoader;

public class ReceiverThread implements Runnable{

    @Override
    public void run() {

        try {
            JChannel channel = new JChannel();
            channel.setReceiver(new Receiver() {
                public void receive(Message msg){
                    System.out.println(msg);
                }
            });
            channel.connect(ResourceLoader.getJGroupsClusterName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
