import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

// Nuevo hilo de procesamiento
// Recibe same buffer as consumer from Buffer.java
public class Producer extends Thread {
    Buffer buffer;
    
    int prodWaitTime;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    Producer(Buffer buffer, int prodWaitTime) {
        this.buffer = buffer;
        this.prodWaitTime = prodWaitTime;
    }
    
    // Requieren un referencia del almacen ade donde van a estar trabajando (buffer)
    
    
    //Instancia de un hilo independiente
    //Metodo run que se puede ejecutar de manera parelela
    
    // Publica vacio run() sobreescribir
    
    @Override
    public void run() {
        //Anunciar la ejecucion
        System.out.println("Running Producer...");
        //Producir una vocal
        // Difinir string por vocales
        String[] products = {"(+ 1 1)", "(+ 1 2)", "(+ 1 3)", "(+ 1 4)"};
        // Random object
        Random r = new Random(System.currentTimeMillis());
        String product;
        
        for(;;) {
            // Get random char from products 
            product = products[(r.nextInt(4))];
            //Store the product the original buffer from this object
            // No garantizado que el buffer tenga espacio
            // vaciar buffer si algun consumidor toma el producto
            this.buffer.produce(product);
            System.out.println("Producer produced: " + product);
            // Buffer.print("Producer produced: " + product); //impresion sincronizada
            
            try {
                Thread.sleep(this.prodWaitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
