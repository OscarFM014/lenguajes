import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;


public class Buffer {
    
    // Espacio, de tamano uno de tamano char solo uno a la vez
    private LinkedList<String> list;
    int capacity = 2;
    
    Buffer() {
        // Forma vacia
        this.list = new LinkedList<>();
    }
    
    synchronized String consume() {
        String product;
        
        while(this.list.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Consumir
        product = this.list.removeFirst();

        notifyAll();
        
        return product;
    }
    
    
    // Generar contexto synchronized, concurrency-paralelism
    synchronized void produce(String product) {
        while(this.list.size() == capacity) {
            try {
                // wait() salir hasta que se pueda like a while
                // multiples productores en este punto de espera
                
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.list.add(product);
        System.out.println(this.list.toString());
        
        // mas de un productor o consumidor
        notifyAll();
    }
}
