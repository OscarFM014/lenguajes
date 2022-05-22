import java.util.logging.Level;
import java.util.logging.Logger;

// Same struct as Producer.java
// Recibe same buffer as producer from buffer.java
public class Consumer extends Thread {
    Buffer buffer;
    
    int consumWaitTime;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    Consumer(Buffer buffer, int consumWaitTime) {
        this.buffer = buffer;
        this.consumWaitTime = consumWaitTime;

    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        for(;;) {
            product = this.buffer.consume();
            System.out.println("Consumer consumed: " + product);
            //Buffer.print("Consumer consumed: " + product);
            
            try {
                Thread.sleep(this.consumWaitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
