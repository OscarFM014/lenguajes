


import java.util.logging.Level;
import java.util.logging.Logger;

// Same struct as Producer.java
// Recibe same buffer as producer from buffer.java
public class Consumer extends Thread {
    Buffer buffer;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        for(int i=0 ; i<5 ; i++) {
            product = this.buffer.consume();
            System.out.println("Consumer consumed: " + product);
            //Buffer.print("Consumer consumed: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
