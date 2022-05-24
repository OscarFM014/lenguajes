
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

    public void setNumbers(String num1, String num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public void run() {
        Scheme newScheme = new Scheme();

        String product;

        for (;;) {

            product = newScheme.GenerateRandomOperation(Integer.parseInt(this.num1), Integer.parseInt(this.num2));

            this.buffer.produce(product, this.idProcesor);

            try {
                Thread.sleep(this.prodWaitTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); 
                break;
            }
        }
    }

}
