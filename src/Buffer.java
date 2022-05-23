import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class Buffer {

    // Espacio, de tamano uno de tamano char solo uno a la vez
    private LinkedList<ArrayList<String>> list;
    int bufferSize;

    Buffer() {
        this.list = new LinkedList<>();
        // StartScheme();

        Scheme newScheme = new Scheme();
        System.out.println(newScheme.SolveOperationScheme("(* 9 9)"));
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
        // Consumir
        product = this.list.removeFirst();
        product.add(Integer.toString(idconsumer));

        notifyAll();
        return product;
    }

    // Generar contexto synchronized, concurrency-paralelism
    synchronized void produce(String product, int idprocesor) {
        while (this.list.size() == bufferSize) {
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

    /*
     * public void StartScheme(){
     * Runtime runtime = Runtime.getRuntime();
     * try {
     * Runtime rt = Runtime.getRuntime();
     * 
     * String[] commands = {"C:\\Program Files\\Racket\\Racket.exe"};
     * myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
     * 
     * 
     * SolveOperationScheme(myPR, "(+ 1 2)");
     * //Double t1 = SolveOperationScheme(myPR, "(+ 5 8)");
     * //t1 += 20;
     * System.out.println("SORT OF : ");
     * myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
     * SolveOperationScheme(myPR, "(* 6 7)");
     * myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
     * SolveOperationScheme(myPR, "(/ 0 0)");
     * myPR = rt.exec(commands, null, new File("C:\\Program Files\\Racket\\"));
     * SolveOperationScheme(myPR, "(/ 0 6)");
     * } catch (IOException ex) {
     * Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
     * }
     * }
     * 
     * public Double SolveOperationScheme(Process pro, String operation) throws
     * IOException{
     * if(operation.charAt(5) == '0'){
     * System.out.println("IMAGINARY DIVISION : 0");
     * return 0.0;
     * }
     * System.out.println("Entered solving scheme operation : " + operation);
     * 
     * BufferedWriter bWriter =
     * new BufferedWriter(new OutputStreamWriter(pro.getOutputStream()));
     * 
     * System.out.println("Reached 1");
     * 
     * BufferedReader stdInput = new BufferedReader(new
     * InputStreamReader(pro.getInputStream()));
     * 
     * System.out.println("Reached 2");
     * 
     * BufferedReader stdError = new BufferedReader(new
     * InputStreamReader(pro.getErrorStream()));
     * 
     * System.out.println("Reached 3");
     * 
     * String answer = null;
     * int counter = 0;
     * 
     * //System.out.println("About to enter while " + stdInput.readLine());
     * 
     * while ((answer = stdInput.readLine()) != null && counter < 2) {
     * //while ( stdInput.ready() && counter < 2) {
     * //answer = stdInput.readLine();
     * System.out.println(answer);
     * System.out.println("COOL");
     * //pr = rt.exec("help");
     * //pr = rt.exec("5");
     * //rt.exec("(+ 1 2)");
     * //rt.exec("Hola");
     * 
     * //p_stdin.write("( + 6 2)");
     * //p_stdin.write(GenerateRandomOperation());
     * String Operation = operation;
     * System.err.println("CHECKING: " + Operation + "dONE");
     * bWriter.write(Operation);
     * 
     * bWriter.newLine();
     * bWriter.flush();
     * counter++;
     * }
     * System.out.println(answer.charAt(0));
     * System.out.println("FINISHED SCHEME TEST");
     * 
     * answer = answer.substring(1);
     * Double.parseDouble(answer);
     * System.out.println("Answer : " + Double.parseDouble(answer));
     * //return 0.0;
     * return Double.parseDouble(answer);
     * }
     */
}
