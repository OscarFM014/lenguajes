import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// Same struct as Producer.java
// Recibe same buffer as producer from buffer.java
public class Consumer extends Thread {
    Buffer buffer;
    
    int consumWaitTime;
    int idConsumer;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    Consumer(Buffer buffer, int consumWaitTime) {
        this.buffer = buffer;
        this.consumWaitTime = consumWaitTime;

    }
    
    public void setIdConsumer(int idConsumer){
        this.idConsumer = idConsumer;
    }
    
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        ArrayList<String> product = new ArrayList<>();
        
        for(;;) {
            product = this.buffer.consume(this.idConsumer);
            ArrayList<String> res = new ArrayList<>();
            res.add(Integer.toString(this.idConsumer));
            res.add(product.get(0));
            res.add("Resultado");

            GUIFrame.updateJTable2(res);
            
            try {
                Thread.sleep(this.consumWaitTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // restore interrupted status
                break;
            }
        }
    }
}