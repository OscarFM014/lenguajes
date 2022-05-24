import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Buffer {

    private LinkedList<ArrayList<String>> list;
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

        while (this.list.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
        product = this.list.removeFirst();
        product.add(Integer.toString(idconsumer));

        notifyAll();
        return product;
    }

    
    synchronized void produce(String product, int idprocesor) {
        while (this.list.size() == bufferSize) {
            try {
   
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
