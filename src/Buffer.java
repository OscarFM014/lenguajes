import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Buffer {
    // Espacio, de tamano uno de tamano char solo uno a la vez
    private LinkedList<ArrayList <String>> list;
    int bufferSize;
    
    Buffer() {
        this.list = new LinkedList<>();
    }
    
    Buffer(int bufferSize) { 
        this.list = new LinkedList<>();
        this.bufferSize = bufferSize;
    }
    
    synchronized ArrayList<String> consume(int idconsumer) {
        ArrayList<String> product;
        
        while(this.list.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Consumir
        product = this.list.removeFirst();
        product.add(Integer.toString(idconsumer));
            
        notifyAll();
        return product;
    }
    
    
    // Generar contexto synchronized, concurrency-paralelism
    synchronized void produce(String product, int idprocesor) {
        while(this.list.size() == bufferSize) {
            try {
                // wait() salir hasta que se pueda like a while
                // multiples productores en este punto de espera
                
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ArrayList<String> myvalues = new ArrayList<>();
        myvalues.add(product);
        myvalues.add(Integer.toString(idprocesor));
        this.list.add(myvalues);
        
       GUIFrame.updateJTable1(this.list, this.bufferSize);
        
        notifyAll();
    }

}