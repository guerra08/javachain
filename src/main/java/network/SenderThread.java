package network;

import org.jgroups.JChannel;
import utils.ResourceLoader;

public class SenderThread implements Runnable {

    @Override
    public void run() {
        JChannel channel = connect();
        while(!Thread.currentThread().isInterrupted()){
            try {
                channel.send(null, "Something in the way");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private JChannel connect(){
        JChannel channel = null;
        try {
            channel = new JChannel();
            channel.connect(ResourceLoader.getJGroupsClusterName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channel;
    }
}
