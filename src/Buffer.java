


import java.util.logging.Level;
import java.util.logging.Logger;


public class Buffer {
    
    // Espacio, de tamano uno de tamano char solo uno a la vez
    private char buffer;
    
    Buffer() {
        // Forma vacia
        this.buffer = 0;
    }
    
    synchronized char consume() {
        char product = 0;
        
        while(this.buffer == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Consumir
        product = this.buffer;
        this.buffer = 0;
        
        notifyAll();
        
        return product;
    }
    
    
    
    // Generar contexto synchronized, concurrency-paralelism
    synchronized void produce(char product) {
        while(this.buffer != 0) {
            try {
                // wait() salir hasta que se pueda like a while
                // multiples productores en este punto de espera
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;
        
        // mas de un productor o consumidor
        notifyAll();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
