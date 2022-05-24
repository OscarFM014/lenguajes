import java.util.ArrayList;

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
        ArrayList<String> product = new ArrayList<>();
        Scheme newScheme = new Scheme();
        
        for(;;) {
            product = this.buffer.consume(this.idConsumer);
            
            this.resultado = Double.toString(newScheme.SolveOperationManual(product.get(0)));
            
            ArrayList<String> res = new ArrayList<>();
            res.add(Integer.toString(this.idConsumer));
            res.add(product.get(0));
            res.add(this.resultado);

            GUIFrame.updateJTable2(res);
            
            try {
                Thread.sleep(this.consumWaitTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}