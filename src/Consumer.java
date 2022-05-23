import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

// Same struct as Producer.java
// Recibe same buffer as producer from buffer.java
public class Consumer extends Thread {
    Buffer buffer;
    
    int consumWaitTime;
    int idConsumer;
    String resultado;
    
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
            Scheme newScheme = new Scheme();
        
            try {
                this.resultado = Double.toString(newScheme.SolveOperationScheme(product.get(0)));
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<String> res = new ArrayList<>();
            res.add(Integer.toString(this.idConsumer));
            res.add(product.get(0));
            res.add(this.resultado);

            GUIFrame.updateJTable2(res);
            
            try {
                Thread.sleep(this.consumWaitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}