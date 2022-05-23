
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int prodWaitTime;
    int idProcesor;
    String num1, num2;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    Producer(Buffer buffer, int prodWaitTime) {
        this.buffer = buffer;
        this.prodWaitTime = prodWaitTime;
    }

    public void setIdProcesor(int idProcesor) {
        this.idProcesor = idProcesor;
    }
    
    public void setNumbers(String num1, String num2){
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        
        System.out.println("Running Producer...");
        
        Scheme newScheme = new Scheme();
     
        String product;

        for (;;) {

            product = newScheme.GenerateRandomOperation(Integer.parseInt(this.num1), Integer.parseInt(this.num2));

            this.buffer.produce(product, this.idProcesor);
            
            try {
                Thread.sleep(this.prodWaitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
     * public String GenerateRandomOperation(){
     * String myOperation = "(";
     * Random r = new Random(System.currentTimeMillis());
     * String[] operatorOptions = {"+", "-", "*", "/"};
     * myOperation += operatorOptions[(r.nextInt(4))];
     * r = new Random(System.currentTimeMillis());
     * 
     * myOperation += " ";
     * 
     * String[] operandOptions = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
     * myOperation += operandOptions[(r.nextInt(10))];
     * 
     * myOperation += " ";
     * 
     * myOperation += operandOptions[(r.nextInt(10))];
     * myOperation += ")";
     * 
     * System.out.println(myOperation);
     * return "";
     * }
     */

}
