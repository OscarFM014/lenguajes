import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;


public class Buffer {
    
    // Espacio, de tamano uno de tamano char solo uno a la vez
    private LinkedList<String> list;
    int capacity = 2;
    
    Process myPR;
    
    Buffer() {
        // Forma vacia
        this.list = new LinkedList<>();
        StartScheme();
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
    
    public void StartScheme(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Runtime rt = Runtime.getRuntime();

            String[] commands = {"C:\\Program Files\\Racket\\Racket.exe"};
                myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));


            SolveOperationScheme(myPR, "(+ 1 2)");
            //Double t1 = SolveOperationScheme(myPR, "(+ 5 8)");
            //t1 += 20;
            System.out.println("SORT OF : ");
            myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
            SolveOperationScheme(myPR, "(* 6 7)");
        } catch (IOException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Double SolveOperationScheme(Process pro, String operation) throws IOException{
        System.out.println("Entered solving scheme operation : " + operation);
        
        BufferedWriter bWriter = 
        new BufferedWriter(new OutputStreamWriter(pro.getOutputStream()));

        System.out.println("Reached 1");
        
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(pro.getInputStream())); 
        
        System.out.println("Reached 2");
        
        BufferedReader stdError = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
        
        System.out.println("Reached 3");
        
        String answer = null;
        int counter = 0;
        
        //System.out.println("About to enter while " + stdInput.readLine());
        
        while ((answer = stdInput.readLine()) != null && counter < 2) {
        //while ( stdInput.ready() && counter < 2) {
            //answer = stdInput.readLine();
            System.out.println(answer);
            System.out.println("COOL");
            //pr = rt.exec("help");
            //pr = rt.exec("5");
            //rt.exec("(+ 1 2)");
            //rt.exec("Hola");

            //p_stdin.write("( + 6 2)");
            //p_stdin.write(GenerateRandomOperation());
            String Operation = operation;
            System.err.println("CHECKING: " + Operation + "dONE");
            bWriter.write(Operation);

            bWriter.newLine();
            bWriter.flush();
            counter++;
        }
        
        System.out.println("FINISHED SCHEME TEST");
        return 0.0;
    }
}
